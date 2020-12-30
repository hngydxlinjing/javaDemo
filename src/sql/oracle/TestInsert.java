package sql.oracle;

import io.netty.util.internal.StringUtil;
import org.junit.Test;
import sql.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @author linjing
 * @date: Created in 2020/11/5
 */
public class TestInsert {

    @Test
    public void  test() throws SQLException {
        StringBuffer createTransferAttachSql = new StringBuffer();
        createTransferAttachSql.append("insert into gjdfppos.fund_transfer_attach(")
                .append("transfer_id,data_type,create_time,create_user,updated_Flag,review_flag,glr_Receive_Count)")
                .append("values (?,?,?,?,?,?,?)");
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(createTransferAttachSql.toString());

        Date date = new Date();
        Timestamp tt = new Timestamp(date.getTime());

        ps.setInt(1,43526);
        ps.setString(2,"1");
        ps.setDate(3,new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        ps.setInt(4,17960);
        ps.setString(5,"0");
        ps.setString(6,"0");
        ps.setInt(7,0);

        ps.executeUpdate();			//执行sql语句
    }


}

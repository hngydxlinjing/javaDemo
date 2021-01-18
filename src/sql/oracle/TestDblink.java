package sql.oracle;

import org.junit.Test;
import sql.JDBCUtils;

import java.io.IOException;
import java.sql.*;

/**
 * @author linjing
 * @date: Created in 2020/9/9
 * 测试目的：
 * 1、Oracle 普通查询会开启事务吗? --不会
 * 2、Oracle dblink中查询会开启事务吗? --oracle会自动开启事务
 * 3、Oracle dblink查询事务会自动关闭吗? -- 如果conn不显示提交或者关闭，事务不会自动提交的问题。只能靠DCD或Tcp KeepLive机制触发数据库销毁会话
 * --oracle 事务查询
 * select  TO_CHAR(c.START_DATE,'YYYY-MM-DD HH24:MI:SS') as START_TIME_F,
 *         to_char(c.UBASQN,'XXXXXXXXXX') as UBASQN_HEX,
 *         to_char(c.UBAREC,'XXXXXXXXXX') as UBAREC_HEX,
 *         c.XID, c.xidusn, c.xidslot, c.xidsqn,
 *         a.username,b.name, a.MACHINE, a.PROGRAM,
 *         c.*
 *         from v$session a,v$rollname b,v$transaction c where a.saddr=c.ses_addr and b.usn=c.xidusn
 *         order by c.START_DATE desc;
 *
 *
 * -- Create table
 * create table TEST_LJ
 * (
 *   name VARCHAR2(100)
 * );
 */
public class TestDblink {
    @Test
    public void test1() throws SQLException, IOException {
        Connection conn = JDBCUtils.getConnection();
        //设置不自动提交
        conn.setAutoCommit(false);
        //1、普通查询
        Statement stmt = conn.createStatement() ;
        String sql = "select * from dual";
        ResultSet rs = stmt.executeQuery(sql) ;
        while(rs.next()){
            String pass = rs.getString(1) ; // 此方法比较高效
            System.out.println(pass);
        }
        // conn.commit();
       /* stmt.close();
        rs.close();
        conn.close();*/
       System.out.println("普通查询，显式提交事务，oracle不会产生事务！");
        System.in.read();
    }

    @Test
    public void test2() throws SQLException, IOException {
        Connection conn = JDBCUtils.getConnection();
        //设置不自动提交
        conn.setAutoCommit(true);
        //1、普通查询
        Statement stmt = conn.createStatement() ;
        String sql = "select * from dual";
        ResultSet rs = stmt.executeQuery(sql) ;
        while(rs.next()){
            String pass = rs.getString(1) ; // 此方法比较高效
            System.out.println(pass);
        }
       /* stmt.close();
        rs.close();
        conn.close();*/
        System.out.println("普通查询，自动提交事务，oracle不会产生事务！");
        System.in.read();
    }

    @Test
    public void error1()  throws SQLException, IOException{
        Connection conn = JDBCUtils.getConnection();
        //设置不自动提交
        conn.setAutoCommit(false);
        //2、dblink查询
        String sql2 = "select * from vtaat_pftperiodctrl";
        Statement stmt2 = conn.createStatement() ;
        ResultSet rs2 =  stmt2.executeQuery(sql2) ;
        while(rs2.next()){
            String name = rs2.getString("c_fundcode") ;
            String pass = rs2.getString(1) ; // 此方法比较高效
            System.out.println(name+"  "+pass);
        }
        // conn.commit();
       /* stmt.close();
        rs.close();
        conn.close();*/
        System.out.println("dblink查询，设置显式提交事务不提交，oracle会产生事务,且不会自动释放！！！");
        System.in.read();
    }

    @Test
    public void error2()  throws SQLException, IOException{
        Connection conn = JDBCUtils.getConnection();
        //设置不自动提交
        conn.setAutoCommit(true);
        //2、dblink查询
        String sql2 = "select * from vtaat_pftperiodctrl";
        Statement stmt2 = conn.createStatement() ;
        ResultSet rs2 =  stmt2.executeQuery(sql2) ;
        while(rs2.next()){
            String name = rs2.getString("c_fundcode") ;
            String pass = rs2.getString(1) ; // 此方法比较高效
            System.out.println(name+"  "+pass);
        }
        // conn.commit();
       /* stmt.close();
        rs.close();
        conn.close();*/
        System.out.println("dblink查询，设置自动提交事务，oracle会产生事务,且不会自动释放！！！");
        System.in.read();
    }

    @Test
    public void testInsert1()  throws SQLException, IOException{
        Connection conn = JDBCUtils.getConnection();
        //设置自动提交
        conn.setAutoCommit(true);
        //2、dblink插入
        String sql2 = "insert into  test_lj@dfta_link(name) values ('linjing')";
        PreparedStatement pstmt = conn.prepareStatement(sql2) ;
        int count = pstmt.executeUpdate(sql2) ;
        System.out.println(count);
        // conn.commit();
       /* stmt.close();
        rs.close();
        conn.close();*/
        System.out.println("dblink存储过程，设置自动提交事务，oracle会产生事务,会自动释放！！！这点与查询dblink不同，查询dblink不会自动释放！");
        System.in.read();
    }

    @Test
    public void testInsert2()  throws SQLException, IOException{
        Connection conn = JDBCUtils.getConnection();
        //设置自动提交
        conn.setAutoCommit(false);
        //2、dblink插入
        String sql2 = "insert into  test_lj@dfta_link(name) values ('linjing')";
        PreparedStatement pstmt = conn.prepareStatement(sql2) ;
        int count = pstmt.executeUpdate(sql2) ;
        System.out.println(count);
        // conn.commit();
       /* stmt.close();
        rs.close();
        conn.close();*/
        System.out.println("dblink存储过程，设置显式提交事务，oracle会产生事务,不会自动释放！！！");
        System.in.read();
    }

    @Test
    public void testInsert3()  throws SQLException, IOException{
        Connection conn = JDBCUtils.getConnection();
        //设置自动提交
        conn.setAutoCommit(false);
        //2、dblink插入
        String sql2 = "insert into  test_lj@dfta_link(name) values ('linjing')";
        PreparedStatement pstmt = conn.prepareStatement(sql2) ;
        int count = pstmt.executeUpdate(sql2) ;
        System.out.println(count);
         conn.commit();
       /* stmt.close();
        rs.close();
        conn.close();*/
        System.out.println("dblink存储过程，设置显式提交事务，且提交，oracle会产生事务,会自动释放！！！");
        System.in.read();
    }


    @Test
    public void  right()  throws SQLException, IOException{
        Connection conn= null;
        ResultSet rs2 = null;
        Statement stmt2 = null;
        try {
             conn = JDBCUtils.getConnection();
            //设置不自动提交
            conn.setAutoCommit(false);
            //2、dblink查询
            String sql2 = "select * from vtaat_pftperiodctrl";
             stmt2 = conn.createStatement();
             rs2 = stmt2.executeQuery(sql2);
            while (rs2.next()) {
                String name = rs2.getString("c_fundcode");
                String pass = rs2.getString(1); // 此方法比较高效
                System.out.println(name + "  " + pass);
            }
            conn.commit();
            System.out.println("dblink视图，oracle commit后,会自动释放！！！");
        }finally {
            stmt2.close();
            rs2.close();
            conn.close();
        }
        System.in.read();
    }


}

package designModel.行为型模式.观察者模式.拦截器;

/**
 * @author linjing
 * @date: Created in 2020/11/20
 */
public class App {
    public static void main(String[] args) {
        App app = new App();
        Request processedRequest =new Request("1");
        HandlerExecutionChain  mappedHandler = null;
        try {
            mappedHandler = app.getHandler(processedRequest);
            if (mappedHandler != null && mappedHandler.getHandler() != null) {
                Node node =(Node) mappedHandler.getHandler();
                HandlerInterceptor[] interceptors = mappedHandler.getInterceptors();
                int i;
                int interceptorIndex = -1;
                HandlerInterceptor interceptor;
                if (interceptors != null) {
                    for(i = 0; i < interceptors.length; interceptorIndex = i++) {
                        interceptor = interceptors[i];
                        if (!interceptor.preHandle(processedRequest, mappedHandler.getHandler())) {
                            app.triggerAfterCompletion(mappedHandler, interceptorIndex, processedRequest, (Exception)null);
                            return;
                        }
                    }
                }
                node.exe();

                app.triggerAfterCompletion(mappedHandler, interceptorIndex, processedRequest,  (Exception)null);
            }

            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void triggerAfterCompletion(HandlerExecutionChain mappedHandler, int interceptorIndex, Request request, Exception ex) {
        if (mappedHandler != null) {
            HandlerInterceptor[] interceptors = mappedHandler.getInterceptors();
            if (interceptors != null) {
                for(int i = interceptorIndex; i >= 0; --i) {
                    HandlerInterceptor interceptor = interceptors[i];

                    try {
                        interceptor.afterCompletion(request,  mappedHandler.getHandler(), ex);
                    } catch (Throwable var10) {
                        var10.printStackTrace();
                        //this.logger.error("HandlerInterceptor.afterCompletion threw exception", var10);
                    }
                }
            }
        }
    }

    private HandlerExecutionChain getHandler(Request processedRequest) {
        String nodeId = processedRequest.getNodeId();
        Node node = getNodeById(nodeId);
        HandlerExecutionChain chain = new HandlerExecutionChain(node);
        chain.addInterceptors(this.getInteceptorsByNodeId(nodeId));
        return  chain;
    }

    private HandlerInterceptor[] getInteceptorsByNodeId(String nodeId) {
        if(nodeId == null){
            return null;
        }
        if("1".equals(nodeId)){
            return new HandlerInterceptor[]{new Node1HandlerInterceptor_X(),new Node1HandlerInterceptor_Y(),new Node1_HandlerInterceptor_Z()};
        }else if("2".equals(nodeId)){
            return new HandlerInterceptor[]{new Node2HandlerInterceptor()};
        }
        return null;
    }

    private Node getNodeById(String nodeId) {
        return new Node(nodeId,"节点"+nodeId);
    }


}

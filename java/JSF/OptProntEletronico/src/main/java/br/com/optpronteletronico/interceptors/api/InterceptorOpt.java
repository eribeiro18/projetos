package br.com.optpronteletronico.interceptors.api;

import br.com.optpronteletronico.interceptors.impl.Required;
import br.com.optpronteletronico.exception.OPTException;
import br.com.optpronteletronico.interceptors.impl.InterceptorOptImpl;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;


/**
 *
 * @author evandro
 */
@Interceptor @InterceptorOptImpl
public class InterceptorOpt implements Serializable{
   
    @AroundInvoke
    public Object validateObject(InvocationContext context) throws Exception{
        Object msg = null;
        Object[] parameters = context.getParameters();
        
        Logger.getLogger(InterceptorOpt.class.getSimpleName()).log(Level.INFO, "Interceptando persistência");
        for(Object parameter : parameters){
            if(parameter instanceof Object[]){//Se for um array de dados
                Object[] parametersAux = (Object[])parameter;
                for(Object p : parametersAux){
                    msg = validarDadosObjeto(p);
                }
            }else{
                msg = validarDadosObjeto(parameter);
            }   
            if(msg != null){
                break;
            }
        }
        if(msg != null){
            throw new OPTException((String)msg);
        }else{
            return context.proceed();
        }
    }

    public String validarDadosObjeto(Object data){
        Field[] fields = data.getClass().getDeclaredFields();
        String info = null;
        for(Field field : fields){
            field.setAccessible(true);
            if(field.getDeclaredAnnotations().length > 0){//Analisando se campo possui annotações
                Annotation[] annotations = field.getDeclaredAnnotations();
                for(Annotation annotation : annotations){
                    if(annotation instanceof Required){
                        Required sicRequired = (Required) annotation;
                        info = checkSicRequired(sicRequired, field, data, info);
                        if(info!=null){
                            return info;
                        }
                    }
                }
            }
        }
        return info;
    }
    
    private String checkSicRequired(Required sicRequired, Field field, Object data, String info){
        try {
            if(sicRequired.isRequired() && (field.get(data) == null || field.get(data).equals(""))){//Campo com preenchimento o obrigatório
                info = sicRequired.msg()+"("+sicRequired.campo()+")";
                return info;
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(InterceptorOpt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
//    private String checkFieldJoinColumn(Annotation annotation, Required sicRequired, Field field, Object data, String info){
//        JoinColumn joinColumn = (JoinColumn) annotation;
//        try {
//            if(!joinColumn.nullable()){//Campo com preenchimento o obrigatório
//                char initField  = field.getName().substring(0,1).toUpperCase().toCharArray()[0];
//                String nameField= field.getName().substring(1, field.getName().length());
//                try {
//                    Method method = data.getClass().getDeclaredMethod("get"+initField+nameField);
//                    Object dataAux= method.invoke(data);
//                    
//                    Method methodId = dataAux.getClass().getDeclaredMethod("getId");
//                    Object id       = methodId.invoke(dataAux);
//                    if(id == null){
//                        info = sicRequired.msg()+"("+sicRequired+")";
//                        return info;
//                    }
//                } catch (NoSuchMethodException | InvocationTargetException | SecurityException ex) {
//                    Logger.getLogger(InterceptorOpt.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        } catch (IllegalArgumentException | IllegalAccessException ex) {
//            Logger.getLogger(InterceptorOpt.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
}
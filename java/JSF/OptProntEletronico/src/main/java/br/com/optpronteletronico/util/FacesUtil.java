/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.util;

import br.com.optpronteletronico.exception.OPTException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.cfg.NotYetImplementedException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author evandro
 */
public abstract class FacesUtil  {

//    extends FacesCommons
    
    //private boolean manyToOne;
    private FacesContext facesContext;
    private ExternalContext externalContext;
    private Map<Class, Object> instances;
    private boolean novo = false;
    private boolean salvar = false;
    private boolean consulta = false;
    private boolean excluir = false;

    public void listarCollection() throws OPTException {
    }

    ;
    public void processAction(ActionEvent event) throws AbortProcessingException {
        throw new NotYetImplementedException("Método herdado");
    }

    public void selectRow(Object o) throws AbortProcessingException {
        throw new NotYetImplementedException("Método herdado");
    }

    public void limparCampos(RequestContext... reqs) {
        throw new NotYetImplementedException("Método herdado");
    }

//    public LinkedHashSet<Object> getValueOfListJSON(String json, Class clazz, Object o) {
//        ObjectMapper mapper = new ObjectMapper();
//        LinkedHashSet<Object> entities = new LinkedHashSet<>();
//        try {
//            Object value = mapper.readValue(json, clazz);
//            List collection = (List) value;
//            collection.stream().forEach(c -> {
//                LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) c;
//                map.entrySet().stream().forEach(data -> {
//                    try {
//                        Field f = o.getClass().getDeclaredField(data.getKey());
//                        f.setAccessible(true);
//                        Annotation[] annotations = f.getDeclaredAnnotations();
//
//                        this.manyToOne = false;
//                        if (annotations.length > 0) {
//                            Arrays.asList(annotations).forEach(a -> {
//                                if (a.annotationType().getSimpleName().equals("ManyToOne")) {
//                                    try {
//                                        Object aux = f.getType().newInstance();
//                                        Field fAux = aux.getClass().getDeclaredField("id");
//                                        fAux.setAccessible(true);
//                                        BigDecimal b = new BigDecimal(Integer.parseInt(data.getValue().toString()));
//                                        fAux.set(aux, b);
//                                        f.set(o, aux);
//                                        this.manyToOne = true;
//                                    } catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException ex) {
//                                        Logger.getLogger(FacesUtil.class.getName()).log(Level.SEVERE, null, ex);
//                                    }
//                                }
//                            });
//                        }
//                        if (!manyToOne) {
//                            if (!data.getValue().getClass().getSimpleName().equals(f.getType().getSimpleName())) {
//                                switch (data.getValue().getClass().getSimpleName()) {
//                                    case "Integer":
//                                        switch (f.getType().getSimpleName()) {
//                                            case "Long":
//                                                f.set(o, new Long(data.getValue().toString()));
//                                                break;
//                                            case "BigDecimal":
//                                                Integer i = new Integer(data.getValue().toString());
//                                                f.set(o, new BigDecimal(i));
//                                                break;
//                                            default:
//                                                break;
//                                        }
//                                        break;
//                                    default:
//                                        break;
//                                }
//                            } else {
//                                f.set(o, data.getValue());
//                            }
//                        }
//                    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
//                        Logger.getLogger(FacesUtil.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                });
//                final Object entity = o;
//                entities.add(entity);
//            });
//        } catch (IOException ex) {
//            Logger.getLogger(FacesUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return entities;
//    }

    protected void page(String root) throws IOException {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(root + ".xhtml");
        } catch (IOException e) {
            throw new IOException("Pagina não econtrada!");
        }
    }

    public void exibirMensagem(FacesMessage.Severity severity, String title, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, msg));
    }

    public RequestContext callGrowlMsg(FacesMessage.Severity severity, String title, String msg) {
        exibirMensagem(severity, title, msg);
        return RequestContext.getCurrentInstance();
    }

    @SuppressWarnings("rawtypes")
    public Object getSessionBean(Class clazzBean) {
        Object sessionBean = null;
        if (clazzBean != null) {
            Annotation[] annotation = clazzBean.getDeclaredAnnotations();
            for (Annotation a : annotation) {
                if (a.annotationType().equals(ManagedBean.class)) {
                    sessionBean = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(((ManagedBean) clazzBean.getDeclaredAnnotations()[0]).name());
                }
            }
        }
        if (sessionBean == null) {
            exibirMensagem(FacesMessage.SEVERITY_FATAL, "Informação", "Erro interno no servidor, por favor entre em contato com o suporte!");
        }
        return sessionBean;
    }

    public Object getSessionBean(String beanName) {
        Object sessionBean = null;
        if (beanName != null) {
            sessionBean = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(beanName);
        }
        if (sessionBean == null) {
            exibirMensagem(FacesMessage.SEVERITY_FATAL, "Informação", "Erro interno no servidor, por favor entre em contato com o suporte!");
        }
        return sessionBean;
    }

    public void addValueSession(String key, Object value) {
        findValueSession(key, value, "put");
    }

    public void removeValueSession(String key) {
        findValueSession(key, null, "remove");
    }

    public Object getSessionValue(String key) {
        return findValueSession(key, null, "get");
    }

    private Object findValueSession(String key, Object value, String... action) {
        String act = action != null && action.length > 0 ? action[0] : null;
        Object data = null;
        boolean breakProcess = false;
        Map<String, Object> jsfSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        for (Map.Entry<String, Object> values : jsfSession.entrySet()) {
            if (values.getKey().equals(key)) {//Valor existente na session, removendo valor
                if (act != null && (act.equals("put") || act.equals("remove"))) {
                    jsfSession.remove(values.getKey());
                } else {
                    data = jsfSession.get(key);
                }
                breakProcess = true;
            }
            if (breakProcess) {
                break;
            }
        }
        if (!breakProcess) {
            Logger.getLogger(FacesUtil.class.getSimpleName()).log(Level.INFO, "Registro não encontrado na session ===> {0}", key);
        }
        if (act != null) {
            switch (act) {
                case "put":
                    jsfSession.put(key, value);
                    break;
                case "get":
                    return data;
                default:
                    break;
            }
        }
        return null;
    }

    public void addParamsCallback(RequestContext req, Object[] keys, Object[] values) {
        int count = 0;
        HashMap<Object, Object> callbackValues = new HashMap<>();
        for (Object key : keys) {
            callbackValues.put(key, values[count]);
            count++;
        }
        this.addParamsCallback(req, callbackValues);
    }

    private void addParamsCallback(RequestContext req, HashMap<Object, Object> values) {
        for (Map.Entry<Object, Object> value : values.entrySet()) {
            req.addCallbackParam(value.getKey().toString(), value.getValue());
        }
    }

    public String currentAction() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        String aux[] = viewId.split("/");
        String name = aux[aux.length - 1].replace(".xhtml", "");
        return name;
    }

    public RequestContext getPrimeRequest() {
        return RequestContext.getCurrentInstance();
    }

    public void primeUpdateContent(RequestContext req, String... ids) {
        for (String id : ids) {
            req.update(id);
        }
    }

    private Map<Class, Object> getInstances() {
        this.instances = new HashMap<>();
        for (Map.Entry<String, Object> values : FacesContext.getCurrentInstance().getViewRoot().getViewMap().entrySet()) {
            this.instances.put(values.getValue().getClass(), values.getValue());
            //System.out.println("Values ===> "+values.getKey() + " - "+values.getValue());
        }
        return instances;
    }

    protected Object[] getInstanceBean() {
        Map<Class, Object> viewsBeans = getInstances();
        Object[] values = new Object[viewsBeans.size()];
        int count = 0;
        for (Map.Entry<Class, Object> instance : viewsBeans.entrySet()) {
            values[count] = instance.getValue();
            count++;
        }
        return values;
    }

    protected List getInstanceBeanASList() {
        Map<Class, Object> viewsBeans = getInstances();
        List values = new ArrayList();
        for (Map.Entry<Class, Object> instance : viewsBeans.entrySet()) {
            values.add(instance.getValue());
        }
        return values;
    }

    protected Object getInstanceBean(Class clazz) {
        Map<Class, Object> viewsBeans = getInstances();
        for (Map.Entry<Class, Object> instance : viewsBeans.entrySet()) {
            if (instance.getKey().equals(clazz)) {
                System.out.println("Key ==> " + instance.getValue());
                return instance.getValue();
            }
        }
        return null;
    }

    protected Object getInstanceBean(Class... clasz) {
        Map<Class, Object> viewsBeans = getInstances();
        for (Class clazz : clasz) {
            for (Map.Entry<Class, Object> instance : viewsBeans.entrySet()) {
                if (instance.getKey().equals(clazz)) {
                    System.out.println("Key ==> " + instance.getValue());
                    return instance.getValue();
                }
            }
        }
        return null;
    }

    protected Object getParamFaces(String nameParam) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(nameParam);
    }

    public FacesContext getFacesContext() {
        if (this.facesContext == null) {
            this.facesContext = FacesContext.getCurrentInstance();
        }
        return facesContext;
    }

    public ExternalContext getExternalContext() {
        if (externalContext == null) {
            this.externalContext = FacesContext.getCurrentInstance().getExternalContext();
        }
        return externalContext;
    }

//    public Set<String> startupSystem(ResourceBundle resourceBundle) {
//        Set<?> keys = resourceBundle.keySet();
//        Set<String> keysValues = new HashSet<>();
//        keys.stream().forEach(key -> {
//            String str;
//            str = key + "-" + resourceBundle.getString(key.toString());
//            keysValues.add(str);
//        });
//        return keysValues;
//    }

//    public Set<String> loadProperties(String nomeProperties) {
//        Set<String> keysValues = null;
////        Logger.getLogger(RuntimeService.class.getSimpleName()).log(Level.WARNING, "START !!!");
//        try {
//            ResourceBundle resourceBundle = ResourceBundle.getBundle(nomeProperties);
//            File f = new File(resourceBundle.getBaseBundleName());
////            System.out.println(f.getAbsolutePath());
////            Logger.getLogger(RuntimeService.class.getSimpleName()).log(Level.WARNING, "BEAN EM EXECUCAO");
//
//            keysValues = this.startupSystem(resourceBundle);
//        } catch (Exception e) {
//            Logger.getLogger(RuntimeService.class.getSimpleName()).log(Level.SEVERE, "FALTA ARQUIVO DE PROPRIEDADES NO DIRETORIO DO GLASSFISH NOME DA PROPRIEDADE " + nomeProperties + "! {0}", e);
//        }
//        return keysValues;
//    }

//    public String getLabelProperties(String nomeProperties, String values) {
//        String str = "";
//        Set<String> keyValues = this.loadProperties(nomeProperties);
//        for (String dmLabel : keyValues) {
//            String[] dms = dmLabel.split("-");
//            if (dms[0].equals(values)) {
//                str = dms[1];
//            }
//        }
//        return str;
//    }

//    public void first() {
//        this.getPageDataTableTemplate().cacheRegistros();
//        if (this.getPageDataTableTemplate().anterior()) {
//            this.getPageDataTableTemplate().setPagina(1);
//            executarConsultaPaginacao();
//        } else {
//            this.exibirMensagem(FacesMessage.SEVERITY_WARN, "Informação", "Não permitido, você jé esta na primeira página!");
//        }
//    }
//
//    public void back() {
//        getPageDataTableTemplate().cacheRegistros();
//        if (getPageDataTableTemplate().getPagina() > getPageDataTableTemplate().getTotalPaginas()) {
//            this.exibirMensagem(FacesMessage.SEVERITY_WARN, "Informação", "N\u00E3o permitido, o n\u00FAmero de p\u00E1gina informada \u00E9 maior que o total de p\u00E1ginas!");
//        } else if (getPageDataTableTemplate().getPagina() == 0) {
//            this.exibirMensagem(FacesMessage.SEVERITY_WARN, "Informação", "N\u00E3o permitido, o n\u00FAmero de p\u00E1gina deve ser maior que zero!");
//        } else {
//            if (getPageDataTableTemplate().anterior()) {
//                executarConsultaPaginacao();
//            } else {
//                this.exibirMensagem(FacesMessage.SEVERITY_WARN, "Informação", "N\u00E3o permitido, voc\u00EA j\u00E1 esta na p\u00E1gina " + getPageDataTableTemplate().getPagina() + "!");
//            }
//        }
//    }

//    public void next() {
//        if (getPageDataTableTemplate().getMaximoRegistros() > 99) {
//            this.exibirMensagem(FacesMessage.SEVERITY_WARN, "Informação", "N\u00E3o permitido, o n\u00FAmero de m\u00E1ximo de registros deve ser menor ou igual a 99!");
//        } else {
//            getPageDataTableTemplate().cacheRegistros();
//            if (getPageDataTableTemplate().getPagina() > getPageDataTableTemplate().getTotalPaginas()) {
//                this.exibirMensagem(FacesMessage.SEVERITY_WARN, "Informação", "N\u00E3o permitido, o n\u00FAmero de p\u00E1gina informada \u00E9 maior que o total de p\u00E1ginas!");
//            } else if (getPageDataTableTemplate().getPagina() == 0) {
//                this.exibirMensagem(FacesMessage.SEVERITY_WARN, "Informação", "N\u00E3o permitido, o n\u00FAmero de p\u00E1gina deve ser maior que zero!");
//            } else {
//                if (getPageDataTableTemplate().proximo()) {
//                    executarConsultaPaginacao();
//                } else {
//                    this.exibirMensagem(FacesMessage.SEVERITY_WARN, "Informação", "N\u00E3o permitido, voc\u00EA j\u00E1 esta na \u00FAltima p\u00E1gina!");
//                }
//            }
//        }
//    }
//
//    public void last() {
//        this.getPageDataTableTemplate().cacheRegistros();
//        if (this.getPageDataTableTemplate().proximo()) {
//            this.getPageDataTableTemplate().setPagina(this.getPageDataTableTemplate().getTotalPaginas());
//            executarConsultaPaginacao();
//        } else {
//            this.exibirMensagem(FacesMessage.SEVERITY_WARN, "Informação", "Não permitido, você jé esta na última página!");
//        }
//    }

//    public void maxPage() {
//        int pagina = this.getPageDataTableTemplate().getPagina();
//        int totalPaginas = this.getPageDataTableTemplate().getTotalPaginas();
//        if (pagina > totalPaginas) {
//            this.exibirMensagem(FacesMessage.SEVERITY_WARN, "Informação", "N\u00E3o permitido, o n\u00FAmero de p\u00E1gina informada \u00E9 maior que o total de p\u00E1ginas! (" + totalPaginas + " é menor que " + pagina + ")");
//        } else {
//            executarConsultaPaginacao();
//        }
//    }

//    public void maxRegistros() {
//        if (getPageDataTableTemplate().getMaximoRegistros() > 99) {
//            this.exibirMensagem(FacesMessage.SEVERITY_WARN, "Informação", "N\u00E3o permitido, o n\u00FAmero de m\u00E1ximo de registros deve ser menor ou igual a 99!");
//        } else {
//            executarConsultaPaginacao();
//        }
//    }

    protected void executarConsultaPaginacao(Object... o) {
    }

    public void exportaZip(Object conteudo, String nomeArquivo, String tipoArquivo) {
        byte[] conteudoParse = null;
        if (conteudo instanceof StringBuilder) {
            conteudoParse = String.valueOf(conteudo).getBytes();
        }
        if (conteudoParse != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream out = new ZipOutputStream(baos);
            out.setLevel(Deflater.DEFAULT_COMPRESSION);
            ZipEntry entry;
            entry = new ZipEntry(nomeArquivo + tipoArquivo);
            try {
                out.putNextEntry(entry);
                out.write(conteudoParse);
                out.closeEntry();
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                processaResponseStream(baos.toByteArray(), nomeArquivo + ".zip", "application/zip");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String toDateTimeString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (date != null) {
            return sdf.format(date);
        }
        return "";

    }

    public void processaResponseStream(byte[] baos, String nomeArquivoZip, String ob) {
        FacesContext faces = FacesContext.getCurrentInstance();
        HttpServletResponse res = (HttpServletResponse) faces.getExternalContext().getResponse();

        try {
            ServletOutputStream out = res.getOutputStream();

            res.setHeader("Content-Disposition", "attachment; filename=\""
                    + nomeArquivoZip + "\"");
            res.setHeader("Pragma", "no-cache");
            res.setHeader("Cache-Control", "no-cache");
            res.setDateHeader("Expires", 0);
            res.setContentType(ob);
            res.setContentLength(baos.length);
            res.getCharacterEncoding();
            out.write(baos, 0, baos.length);
            out.flush();
            out.close();
            res.flushBuffer();
            faces.responseComplete();
            this.exibirMensagem(FacesMessage.SEVERITY_INFO, "Informação", "Arquivo exportado com sucesso!");
        } catch (Exception e) {
            this.exibirMensagem(FacesMessage.SEVERITY_ERROR, "Informação", e.getMessage());
        }
    }

    public boolean isNovo() {
        return novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

    public boolean isSalvar() {
        return salvar;
    }

    public void setSalvar(boolean salvar) {
        this.salvar = salvar;
    }

    public boolean isConsulta() {
        return consulta;
    }

    public void setConsulta(boolean consulta) {
        this.consulta = consulta;
    }

    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }
}
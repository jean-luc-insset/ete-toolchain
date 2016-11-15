package fr.insset.jeanluc.ete.api;


import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import static fr.insset.jeanluc.util.factory.FactoryRegistry.FACTORY_REGISTRY;
import fr.insset.jeanluc.util.hierarchy.Hierarchy;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * An action transforms a model into another model or a package into another
 * package.<br>
 * The process uses an action tree : each node in the tree is an action. The
 * process runs through the tree (depth-first) and executes each action
 * in turn.<br>
 * An action has parameters. A parameter has a name (String) and a value
 * (Object). The parameters are equivalent to local variable in a block view :
 * each action can see its ancestors parameters and a local parameter "hides"
 * ancestor parameters with the same name.<br>
 * Every action must use its own FactoryRegistry instance. When initialized
 * the action sets the thread local FactoryRegistry.registry to a fresh
 * instance. That new instance has the previous value of the thread local as
 * parent. Thus, the registries form a chain. This way, if a component looks
 * for a factory not locally defined, it can find it rnning through the chain
 * of registries.<br>
 * When closed, the action must pop the factory.
 *
 * @author jldeleage
 */
public interface Action {


    /**
     * Name of parameter. This value must be used as a key with the
     * {@link getParameter(String) getParameter(String inParameterName)
     * method}
     */
    public final static String  OUTPUT_BASE      = "output-base";
    /**
     * Name of parameter. This value must be used as a key with the
     * {@link getParameter(String) getParameter(String inParameterName)
     * method}
     */
    public final static String  BASE_URL         = "base-url";



    //========================================================================//
    //                           P R O C E S S I N G                          //
    //========================================================================//


    /**
     * This method should not be overridden.
     * 
     * @param inPackage
     * @return
     * @throws EteException 
     */
    public  default MofPackage  process(MofPackage inPackage) throws EteException {
        MofPackage  result;
        init(inPackage);
        if (shouldIProcess(inPackage)) {
            result = doProcess(inPackage);
        }
        else {
            result = inPackage;
        }
        close();
        return result;
    }


    public  default MofPackage doProcess(MofPackage inPackage) throws EteException {
        inPackage = preProcess(inPackage);
        inPackage = processChildren(inPackage);
        inPackage = postProcess(inPackage);
        return inPackage;
    }


    /**
     * This method should not be overridden.
     * 
     * @param inPackage
     * @throws EteException 
     */
    public default void init(MofPackage inPackage) throws EteException {
        FactoryRegistry.pushNewRegistry();
        readAttributes();
    }


    public default boolean shouldIProcess(MofPackage inPackage) throws EteException {
        return true;
    }


    public default MofPackage preProcess(MofPackage inPackage) throws EteException {
        return inPackage;
    }


    public default MofPackage processChildren(MofPackage inPackage) throws EteException {
        for (Action action : getChildren()) {
            inPackage = action.process(inPackage);
        }
        return inPackage;
    }


    public default MofPackage postProcess(MofPackage inPackage) throws EteException {
        return inPackage;
    }


    /**
     * This method should not be overridden.
     * 
     * @param inPackage
     * @return
     * @throws EteException 
     */
    public default void close() {
        FactoryRegistry.popRegistry();
    }


    //========================================================================//
    //                           P A R A M E T E R S                          //
    //========================================================================//
    // Every action has parameters


    public void    readAttributes() throws EteException;

    public void    addParameter(String inName, Object inValue);

    /**
     * Sets the parameter if it is not already set.<br>
     * Returns the value actually set.
     * 
     * @param inName name of the parameter
     * @param inValue value of the parameter
     * @return 
     */
    public default Object     addDefaultParameter(String inName, Object inValue) {
        Object  value = getParameter(inName);
        if (value != null) {
            return value;
        }
        addParameter(inName, inValue);
        return inValue;
    }


    public default void     addParameters(Map<String, Object> inParameters) {
        for (Map.Entry<String, Object> entry : inParameters.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    };


    public default Object  getParameter(String inName) {
        Object localParameter = getLocalParameter(inName);
        if (localParameter != null) {
            return localParameter;
        }
        Action  parent = getParent();
        if (parent != null) {
            return parent.getParameter(inName);
        }
        return null;
    }

    public Object           getLocalParameter(String inName);


    //========================================================================//
    //           U SU A L    P A R A M E T E R S   S H O R T C U T S          //
    //========================================================================//


    /**
     * Some actions may need to fetch resources based on an url.<br>
     * These actions may have a "base-url" : the URL where to start the search
     * from.<br>
     * 
     * @return 
     */
    public  default String  getBaseUrl() {
        String  result = (String) getParameter(BASE_URL);
        if (result != null) {
            return result;
        }
        return "";
    }


    public default InputStream getResource(String inUrl) throws EteException {
        if (!inUrl.contains(":/")) {
            if (!inUrl.startsWith("/")) {
                String baseUrl = getBaseUrl();
                if (! "".equals(baseUrl)) {
                    if (!baseUrl.endsWith("/")) {
                        baseUrl += "/";
                    }
                    inUrl = baseUrl + inUrl;
                }
            }
            if (!inUrl.startsWith("/")) {
                String workingDirPath = new File(".").getAbsolutePath();
                workingDirPath = workingDirPath.substring(0, workingDirPath.length()-1);
                inUrl = workingDirPath + inUrl;
             }
            inUrl = "file://" + inUrl;
        }
        try {
            URL url = new URL(inUrl);
            return url.openStream();
        } catch (IOException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
            throw new EteException(ex);
        }
    }



    //========================================================================//
    //                            H I E R A R C H Y                           //
    //========================================================================//


    public Action           getParent();
    public void             setParent(Action inParent);

    public Iterable<Action> getChildren() throws EteException;
    public void             addChild(Action inAction);


}



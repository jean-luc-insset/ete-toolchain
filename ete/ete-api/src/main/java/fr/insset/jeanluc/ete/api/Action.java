package fr.insset.jeanluc.ete.api;


import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import static fr.insset.jeanluc.util.factory.FactoryRegistry.FACTORY_REGISTRY;
import fr.insset.jeanluc.util.hierarchy.Hierarchy;
import java.util.Map;


/**
 * An action transforms a model into another model.<br>
 * The process uses an action tree : each node in the tree is an action. The
 * process runs through the tree (depth-first) and executes each action
 * in turn.<br>
 * An action has parameters. A parameter has a name (String) and a value
 * (Object). The parameters are equivalent to local variable in a block view :
 * each action can see its ancestors parameters and a local parameter "hides"
 * ancestor parameters with the same name.<br>
 * Every action must have a FactoryRegistry instance as parameter. The registry
 * is local to the action but the factory registries tree mimetics the
 * action tree.
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
     * Name of factory. This value must be used as a key with the
     * {@link fr.insset.jeanluc.util.factory.FactoryRegistry#getParameter(String)
     * getFactory(String inFactoryName) method}
     */
    public final static String  MODEL            = "model";


    //========================================================================//
    //                           P A R A M E T E R S                          //
    //========================================================================//
    // Every action has parameters


    public void    readAttributes();

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



    public Action           getParent();
    public void             setParent(Action inParent);
    public Iterable<Action> getChildren();
    public void             addChild(Action inAction);



    public  default String  getBaseUrl() {
        String  result = (String) getParameter("base-url");
        if (result != null) {
            return result;
        }
        return "";
    }



    public default FactoryRegistry getFactoryRegistry() {
        return (FactoryRegistry) getParameter(FACTORY_REGISTRY);
    }


    //========================================================================//
    //                           P R O C E S S I N G                          //
    //========================================================================//


    public default EteModel process(EteModel inModel) throws EteException {
        Action parent = getParent();
        FactoryRegistry currentRegistry = null;
        FactoryRegistry previousRegistry;
        if (parent != null) {
            previousRegistry = parent.getFactoryRegistry();
            currentRegistry = previousRegistry.createChild();
            addParameter(FACTORY_REGISTRY, currentRegistry);
        }
        inModel = preProcess(inModel);
        inModel = doProcess(inModel);
        inModel = postProcess(inModel);
        return inModel;
    }


    public default EteModel preProcess(EteModel inModel) throws EteException {
        return inModel;
    }


    public default EteModel doProcess(EteModel inModel) throws EteException {
        return inModel;
    }


    public default EteModel postProcess(EteModel inModel) throws EteException {
        return inModel;
    }


}



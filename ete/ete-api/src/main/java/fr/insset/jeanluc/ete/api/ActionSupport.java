package fr.insset.jeanluc.ete.api;



import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import static fr.insset.jeanluc.util.factory.FactoryRegistry.FACTORY_REGISTRY;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import static javax.swing.text.html.parser.DTDConstants.MODEL;



/**
 * Default implementation of Action.<br>
 * An action can use factories. In such a case, it should get the factory
 * through its local registry. The registry should be stored in the
 * FACTORY_REGISTRY parameter
 *
 * @author jldeleage
 */
public class ActionSupport implements Action {


    public  ActionSupport() {
        // Get the parent's registry
        FactoryRegistry factoryRegistry = FactoryRegistry.getRegistry();
        // and add it to the local parameters
        addParameter(FACTORY_REGISTRY, factoryRegistry);
        // This way the action can register local factories by :
        // getFactoryRegistry().registerFactory(myFactoryName, myFactory);
        // or
        // getFactoryRegistry().registerFactory(myFactoryName, MyBean.class);
    }



    //========================================================================//
    //                              F A C T O R Y                             //
    //========================================================================//


    /**
     *  Convenience method to get the local registry
     */
    public final FactoryRegistry  getFactoryRegistry() {
        return (FactoryRegistry) getParameter(FACTORY_REGISTRY);
    }


    //========================================================================//
    //                                M O D E L                               //
    //========================================================================//

    public EteModel    getModel() {
        return (EteModel)getParameter(MODEL);
    }

    public void        setModel(EteModel inEteModel) {
        addParameter(MODEL, inEteModel);
    }


    //========================================================================//
    //                           P A R A M E T E R S                          //
    //========================================================================//



    public Map<String, Object> getParameters() {
        return parameters;
    }



    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }



    @Override
    public void addParameter(String inName, Object inValue) {
        if (parameters == null) {
            // TODO : use a factory
            parameters = new HashMap<String, Object>();
        }
        parameters.put(inName, inValue);
    }



    @Override
    public void addParameters(Map<String, Object> inParameters) {
        if (parameters == null) {
            parameters = new HashMap<>();
        }
        parameters.putAll(inParameters);
    }



    @Override
    public Object getLocalParameter(String inName) {
        if (parameters == null) {
            return null;
        }
        return parameters.get(inName);
    }



    //========================================================================//
    //                     A C T I O N   H I E R A R C H Y                    //
    //========================================================================//


    @Override
    public void addChild(Action inAction) {
        if (children == null) {
            children = new LinkedList<>();
        }
        if (! children.contains(inAction)) {
            children.add(inAction);
            inAction.setParent(this);
        }
    }


    /**
     * Returns a lazily instanciated collection.<br>
     * Actually, the definition element content is read at this time. This
     * prevents unused readings (in the case of an "if" action for example)
     * 
     * @return 
     */
    @Override
    public Iterable<Action> getChildren() {
        if (children == null) {
            if (reader != null) {
                reader.readChildren(this, parameters);
                return children;
            }
        }
        else {
            return children;
        }
        return Collections.EMPTY_LIST;
    }


    @Override
    public void readAttributes() {
        if (reader != null) {
            reader.readAttributes(this, definition);
        }
    }



    //========================================================================//
    //                           D E F I N I T I O N                          //
    //========================================================================//


    public ActionReader getReader() {
        return reader;
    }

    public void setReader(ActionReader reader) {
        this.reader = reader;
    }



    public Object getDefinition() {
        return definition;
    }

    public void setDefinition(Object definition) {
        this.definition = definition;
    }


    //========================================================================//


    @Override
    public Action getParent() {
        return parent;
    }

    @Override
    public void setParent(Action inAction) {
        parent = (Action) inAction;
        // Get the parent's registry
        FactoryRegistry factoryRegistry = parent.getFactoryRegistry();
        // Create a child registry
        FactoryRegistry localRegistry = factoryRegistry.createChild();
        // and add it to the local parameters
        addParameter(FACTORY_REGISTRY, localRegistry);
        // This way the action can register local factories by :
        // getFactoryRegistry().registerFactory(myFactoryName, myFactory);
        // or
        // getFactoryRegistry().registerFactory(myFactoryName, MyBean.class);
    }



    //========================================================================//
    //                   I N S T A N C E   V A R I A B L E S                  //
    //========================================================================//


    private Collection<Action>      children;
    private Action                  parent;
    private Map<String, Object>     parameters;

    // The object is based on a definition
    private ActionReader            reader;
    private Object                  definition;


}       // XmlActionSupport


package fr.insset.jeanluc.ete.api;



import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.util.factory.FactoryMethods;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import static fr.insset.jeanluc.util.factory.FactoryRegistry.FACTORY_REGISTRY;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.text.html.parser.DTDConstants.MODEL;



/**
 * Default implementation of Action.<br>
 * An action can use factories. When initialized, the action sets the thread
 * local factory registry. When closed, the action reset the local factory
 * registry to its previous value.
 *
 * @author jldeleage
 */
public abstract class ActionSupport implements Action {


    public final static String      ACTION_READER = "action-reader";
    public final static String      MODEL         = "model";


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
    //                                M O D E L                               //
    //========================================================================//


    public MofPackage    getModel() {
        return (MofPackage)getParameter(MODEL);
    }


    public void        setModel(MofPackage inEteModel) {
        addParameter(MODEL, inEteModel);
    }


    //========================================================================//
    //========================================================================//


    public EteModel preProcess(EteModel inModel) {
        return inModel;
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
    //                           D E F I N I T I O N                          //
    //========================================================================//
    // An Action is defined in a configuration document                       //
    // That document may be in any format, as long as a corresponding reader  //
    // is available.                                                          //
    //========================================================================//


    public ActionReader getReader() {
        return (ActionReader)getParameter(ACTION_READER);
    }

    public void setReader(ActionReader reader) {
        addParameter(ACTION_READER, reader);
    }



    public Object getDefinition() {
        return definition;
    }

    public void setDefinition(Object definition) {
        this.definition = definition;
    }



    @Override
    public void readAttributes() throws EteException {
        ActionReader reader = getReader();
        if (reader != null) {
            reader.readAttributes(this, definition);
        }
    }



    //========================================================================//
    //                     A C T I O N   H I E R A R C H Y                    //
    //========================================================================//
    // An action may contain other actions                                    //
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
    public Iterable<Action> getChildren() throws EteException {
        if (children == null) {
            ActionReader reader = getReader();
            if (reader != null) {
                reader.readChildren(this, parameters);
            }
        }
        if (children == null) {
            try {
                children = FactoryMethods.newList(Action.class);
            } catch (InstantiationException ex) {
                Logger.getLogger(ActionSupport.class.getName()).log(Level.SEVERE, null, ex);
                throw new EteException(ex);
            }
        }
        return children;
    }


    @Override
    public Action getParent() {
        return parent;
    }

    @Override
    public void setParent(Action inAction) {
        parent = (Action) inAction;
    }




    //========================================================================//
    //                   I N S T A N C E   V A R I A B L E S                  //
    //========================================================================//


    private Collection<Action>      children;
    private Action                  parent;
    private Map<String, Object>     parameters;

    // The object is based on a definition
    // The definition must be read by an ActionReader
    private Object                  definition;


}       // XmlActionSupport


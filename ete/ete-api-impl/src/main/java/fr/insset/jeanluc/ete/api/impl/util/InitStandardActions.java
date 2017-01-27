package fr.insset.jeanluc.ete.api.impl.util;




import static fr.insset.jeanluc.ete.api.ActionSupport.ACTION_READER;
import fr.insset.jeanluc.ete.api.impl.DumpAction;
import static fr.insset.jeanluc.ete.api.impl.DumpAction.DUMP_ACTION;
import fr.insset.jeanluc.ete.api.impl.ForEachAction;
import static fr.insset.jeanluc.ete.api.impl.ForEachAction.FOR_EACH_ACTION;
import fr.insset.jeanluc.ete.api.impl.IfAction;
import static fr.insset.jeanluc.ete.api.impl.IfAction.IF_ACTION;
import fr.insset.jeanluc.ete.api.impl.ModelAction;
import static fr.insset.jeanluc.ete.api.impl.ModelAction.MODEL_ACTION;
import static fr.insset.jeanluc.ete.api.impl.ModelAction.MODEL_READER;
import fr.insset.jeanluc.ete.api.impl.ModuleAction;
import static fr.insset.jeanluc.ete.api.impl.ModuleAction.MODULE_DEFINITION_ACTION;
import static fr.insset.jeanluc.ete.api.impl.ModuleAction.TRANSFORMATION_SET_ACTION;
import fr.insset.jeanluc.ete.api.impl.ModuleCallAction;
import static fr.insset.jeanluc.ete.api.impl.ModuleCallAction.MODULE_CALL_ACTION;
import fr.insset.jeanluc.ete.api.impl.RegisterAction;
import static fr.insset.jeanluc.ete.api.impl.RegisterAction.REGISTER_ACTION;
import static fr.insset.jeanluc.ete.api.impl.RegisterAction.register;
import fr.insset.jeanluc.ete.api.impl.VelocityAction;
import static fr.insset.jeanluc.ete.api.impl.VelocityAction.VELOCITY_ACTION;
import fr.insset.jeanluc.ete.api.impl.io.XmlActionReader;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import fr.insset.jeanluc.xmi.io.impl.XmlModelReader;




/**
 *
 * @author jldeleage
 */
public class InitStandardActions {



    /**
     * Registers standard actions
     */
    public static void init() {
        FactoryRegistry.register(DUMP_ACTION, DumpAction.class);
        FactoryRegistry.register(FOR_EACH_ACTION, ForEachAction.class);
        FactoryRegistry.register(IF_ACTION, IfAction.class);
        FactoryRegistry.register(MODEL_ACTION, ModelAction.class);
        FactoryRegistry.register(MODULE_CALL_ACTION, ModuleCallAction.class);
        FactoryRegistry.register(MODULE_DEFINITION_ACTION, ModuleAction.class);
        FactoryRegistry.register(REGISTER_ACTION, RegisterAction.class);
        FactoryRegistry.register(TRANSFORMATION_SET_ACTION, ModuleAction.class);
        FactoryRegistry.register(VELOCITY_ACTION, VelocityAction.class);
        FactoryRegistry.register(ACTION_READER, XmlActionReader.class);
        FactoryRegistry.register(MODEL_READER, XmlModelReader.class);
//        register(DUMP_ACTION, "fr.insset.jeanluc.ete.api.impl.DumpAction");
//        register(FOR_EACH_ACTION, "fr.insset.jeanluc.ete.api.impl.ForEachAction");
//        register(IF_ACTION, "fr.insset.jeanluc.ete.api.impl.IfAction");
//        register(MODEL_ACTION, "fr.insset.jeanluc.ete.api.impl.ModelAction");
//        register(MODULE_CALL_ACTION, "fr.insset.jeanluc.ete.api.impl.ModuleCallAction");
//        register(MODULE_DEFINITION_ACTION, "fr.insset.jeanluc.ete.api.impl.ModuleAction");
//        register(REGISTER_ACTION, "fr.insset.jeanluc.ete.api.impl.RegisterAction");
//        register(TRANSFORMATION_SET_ACTION, "fr.insset.jeanluc.ete.api.impl.ModuleAction");
//        register("mda", "fr.insset.jeanluc.ete.api.impl.ProcessorAction");
//        register(VELOCITY_ACTION, "fr.insset.jeanluc.ete.api.impl.VelocityAction");
        register("text-writer", "fr.insset.jeanluc.plain.text.io.TextModelWriter");
//        register(ACTION_READER, "fr.insset.jeanluc.ete.api.impl.io.XmlActionReader");
//        register(MODEL_READER, "fr.insset.jeanluc.xmi.io.impl.XmlModelReader");
    }


}



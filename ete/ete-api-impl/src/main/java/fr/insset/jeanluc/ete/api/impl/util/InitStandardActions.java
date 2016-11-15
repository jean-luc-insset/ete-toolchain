package fr.insset.jeanluc.ete.api.impl.util;




import static fr.insset.jeanluc.ete.api.impl.DumpAction.DUMP_ACTION;
import static fr.insset.jeanluc.ete.api.impl.ForEachAction.FOR_EACH_ACTION;
import static fr.insset.jeanluc.ete.api.impl.IfAction.IF_ACTION;
import static fr.insset.jeanluc.ete.api.impl.ModelAction.MODEL_ACTION;
import static fr.insset.jeanluc.ete.api.impl.ModuleAction.MODULE_DEFINITION_ACTION;
import static fr.insset.jeanluc.ete.api.impl.ModuleAction.TRANSFORMATION_SET_ACTION;
import static fr.insset.jeanluc.ete.api.impl.ModuleCallAction.MODULE_CALL_ACTION;
import fr.insset.jeanluc.ete.api.impl.RegisterAction;
import static fr.insset.jeanluc.ete.api.impl.RegisterAction.REGISTER_ACTION;
import static fr.insset.jeanluc.ete.api.impl.RegisterAction.register;
import static fr.insset.jeanluc.ete.api.impl.VelocityAction.VELOCITY_ACTION;




/**
 *
 * @author jldeleage
 */
public class InitStandardActions {



    /**
     * Registers standard actions
     */
    public static void init() {
        register(DUMP_ACTION, "fr.insset.jeanluc.ete.api.impl.DumpAction");
        register(FOR_EACH_ACTION, "fr.insset.jeanluc.ete.api.impl.ForEachAction");
        register(IF_ACTION, "fr.insset.jeanluc.ete.api.impl.IfAction");
        register(MODEL_ACTION, "fr.insset.jeanluc.ete.api.impl.ModelAction");
        register(MODULE_CALL_ACTION, "fr.insset.jeanluc.ete.api.impl.ModuleCallAction");
        register(MODULE_DEFINITION_ACTION, "fr.insset.jeanluc.ete.api.impl.ModuleAction");
        register(REGISTER_ACTION, "fr.insset.jeanluc.ete.api.impl.RegisterAction");
        register(TRANSFORMATION_SET_ACTION, "fr.insset.jeanluc.ete.api.impl.ModuleAction");
//        register("mda", "fr.insset.jeanluc.ete.api.impl.ProcessorAction");
        register(VELOCITY_ACTION, "fr.insset.jeanluc.ete.api.impl.VelocityAction");

    }


}



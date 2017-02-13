package fr.insset.jeanluc.action.semantics.builder;


import fr.insset.jeanluc.ete.as.api.Statement;
import fr.insset.jeanluc.ete.meta.model.emof.Property;
import fr.insset.jeanluc.gel.api.And;
import fr.insset.jeanluc.util.visit.DynamicVisitorSupport;
import java.util.LinkedList;
import java.util.List;


/**
 * ActionSemanticsBuilder builds ActionSemantics statements for various
 * purposes.<br>
 * Then a dialect can translate these statements into methods or function
 * in a specific language.<br>
 * The actual code generation is then done by a velocity template or a
 * Mof2Text template.<br>
 * So there is no need to build something like a class, a module and so on.
 *
 * @author jldeleage
 */
public class ActionSemanticsCheckerBuilder extends DynamicVisitorSupport {


    public List<Statement>  invariant2Checker() {
        List<Statement>     result = new LinkedList<>();
        return result;
    }





    public Object   visitAnd(And inExp, Object... inParameters) {
        return inExp;
    }


    public List<Statement>  invariant2Select(Property inTarget) {
        List<Statement>     result = new LinkedList<>();
        return result;
    }

}

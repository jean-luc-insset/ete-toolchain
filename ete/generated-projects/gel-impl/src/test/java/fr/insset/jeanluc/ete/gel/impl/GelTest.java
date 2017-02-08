package fr.insset.jeanluc.ete.gel.impl;




import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.gel.impl.factory.GelFactory;
import fr.insset.jeanluc.ete.meta.model.core.PrimitiveDataTypes;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.EteModelImpl;
import static fr.insset.jeanluc.ete.meta.model.types.MofType.MOF_TYPE;
import fr.insset.jeanluc.ete.meta.model.types.impl.MofTypeImpl;
import fr.insset.jeanluc.gel.GelParser;
import fr.insset.jeanluc.gel.GelParser.GelExpressionContext;
import fr.insset.jeanluc.gel.api.GelExpression;
import fr.insset.jeanluc.gel.impl.GelEvaluator;
import fr.insset.jeanluc.gel.impl.*;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;







public class GelTest {


    //========================================================================//

    @Test
    public void testIntLiteral() {
        System.out.println("testIntLiteral");
        testExpression(12, "12", null);
    }


    //========================================================================//



    //========================================================================//


    protected   void    testExpression(Object inExpectedValue, String inExpression, EteModel inContext) {
        try {
            GelParser              parser       = GelParserFactory.newParser(inExpression);
            GelExpressionContext   ctx          = parser.gelExpression();
            if (inContext == null) {
                inContext = GelFactory.newModel();
            }
            TreeBuilder            treeBuilder  = new TreeBuilder(inContext);
            GelExpression          expression   = treeBuilder.visitGelExpression(ctx);
            GelEvaluator evaluator              = new GelEvaluator();
            Object                 result       = evaluator.genericVisit(expression, inContext);
//            assertEquals(inExpectedValue, result);
        } catch (InstantiationException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GelTest.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }


}


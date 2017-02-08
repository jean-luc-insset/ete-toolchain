package fr.insset.jeanluc.ete.gel.impl.factory;



import fr.insset.jeanluc.ete.meta.model.core.PrimitiveDataTypes;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import static fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel.MODEL;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.EteModelImpl;
import static fr.insset.jeanluc.ete.meta.model.types.MofType.MOF_TYPE;
import fr.insset.jeanluc.ete.meta.model.types.impl.MofTypeImpl;
import fr.insset.jeanluc.gel.impl.*;
import fr.insset.jeanluc.util.factory.FactoryRegistry;



public class GelFactory {

    public static void registerFactories() {
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        registry.registerDefaultFactory(MOF_TYPE, MofTypeImpl.class);
                    registry.registerDefaultFactory(">", fr.insset.jeanluc.gel.impl.GreaterThanImpl.class);        
                    registry.registerDefaultFactory("or", fr.insset.jeanluc.gel.impl.OrImpl.class);        
                    registry.registerDefaultFactory("not", fr.insset.jeanluc.gel.impl.NotImpl.class);        
                            registry.registerDefaultFactory("opp", fr.insset.jeanluc.gel.impl.OppImpl.class);        
                        registry.registerDefaultFactory("Integer", IntegerLiteralImpl.class);                
                            registry.registerDefaultFactory("Double", FloatingPointLiteralImpl.class);                
                registry.registerDefaultFactory("<", fr.insset.jeanluc.gel.impl.LessThanImpl.class);        
                            registry.registerDefaultFactory("|", fr.insset.jeanluc.gel.impl.LambdaImpl.class);        
                    registry.registerDefaultFactory("*", fr.insset.jeanluc.gel.impl.MultImpl.class);        
                                registry.registerDefaultFactory("Boolean", BooleanLiteralImpl.class);                
                registry.registerDefaultFactory("and", fr.insset.jeanluc.gel.impl.AndImpl.class);        
                            registry.registerDefaultFactory("=", fr.insset.jeanluc.gel.impl.EqualImpl.class);        
                            registry.registerDefaultFactory(">=", fr.insset.jeanluc.gel.impl.GreaterOrEqualImpl.class);        
                    registry.registerDefaultFactory("-", fr.insset.jeanluc.gel.impl.MinusImpl.class);        
                    registry.registerDefaultFactory("xor", fr.insset.jeanluc.gel.impl.XorImpl.class);        
                        registry.registerDefaultFactory("Date", DateLiteralImpl.class);                
                    registry.registerDefaultFactory("String", StringLiteralImpl.class);                
                registry.registerDefaultFactory("+", fr.insset.jeanluc.gel.impl.AddImpl.class);        
                    registry.registerDefaultFactory("<=", fr.insset.jeanluc.gel.impl.LessOrEqualImpl.class);        
                    registry.registerDefaultFactory("<>", fr.insset.jeanluc.gel.impl.DifferentImpl.class);        
                    registry.registerDefaultFactory("/", fr.insset.jeanluc.gel.impl.DivImpl.class);        
                    }

    public static EteModel      newModel() throws InstantiationException {
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        registry.registerDefaultFactory(MOF_TYPE, MofTypeImpl.class);
        registry.registerDefaultFactory(MODEL, EteModelImpl.class);
        EteModel result = (EteModel) FactoryRegistry.newInstance(MODEL);
        PrimitiveDataTypes.init(result);
        return result;
    }

}



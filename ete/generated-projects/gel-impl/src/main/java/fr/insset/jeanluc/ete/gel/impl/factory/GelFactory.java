package fr.insset.jeanluc.ete.gel.impl.factory;


import fr.insset.jeanluc.util.factory.FactoryRegistry;


public class GelFactory {

    public static void registerFactories() {
        FactoryRegistry registry = FactoryRegistry.getRegistry();
                registry.registerDefaultFactory("*", fr.insset.jeanluc.gel.impl.MultImpl.class);        
                registry.registerDefaultFactory("", fr.insset.jeanluc.gel.impl.LambdaExpressionImpl.class);        
                registry.registerDefaultFactory("+", fr.insset.jeanluc.gel.impl.AddImpl.class);        
                registry.registerDefaultFactory("xor", fr.insset.jeanluc.gel.impl.XorImpl.class);        
                registry.registerDefaultFactory("<>", fr.insset.jeanluc.gel.impl.DifferentImpl.class);        
                registry.registerDefaultFactory("or", fr.insset.jeanluc.gel.impl.OrImpl.class);        
                registry.registerDefaultFactory("/", fr.insset.jeanluc.gel.impl.DivImpl.class);        
                registry.registerDefaultFactory("=", fr.insset.jeanluc.gel.impl.EqualImpl.class);        
                    registry.registerDefaultFactory(">=", fr.insset.jeanluc.gel.impl.GreaterOrEqualImpl.class);        
                        registry.registerDefaultFactory("-", fr.insset.jeanluc.gel.impl.MinusImpl.class);        
                registry.registerDefaultFactory("and", fr.insset.jeanluc.gel.impl.AndImpl.class);        
                    registry.registerDefaultFactory("<=", fr.insset.jeanluc.gel.impl.LessOrEqualImpl.class);        
                registry.registerDefaultFactory("<", fr.insset.jeanluc.gel.impl.LessThanImpl.class);        
                    registry.registerDefaultFactory(">", fr.insset.jeanluc.gel.impl.GreaterThanImpl.class);        
                registry.registerDefaultFactory("not", fr.insset.jeanluc.gel.impl.NotImpl.class);        
                registry.registerDefaultFactory("opp", fr.insset.jeanluc.gel.impl.OppImpl.class);        
            }

}



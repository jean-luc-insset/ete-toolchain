package fr.insset.jeanluc.ete.gel.impl;


import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.ete.meta.model.mofpackage.PackageableElement;
import fr.insset.jeanluc.gel.GelParser;
import fr.insset.jeanluc.gel.GelParserBaseVisitor;
import fr.insset.jeanluc.gel.api.GelExpression;
import fr.insset.jeanluc.gel.api.Literal;
import fr.insset.jeanluc.gel.api.Operation;
import fr.insset.jeanluc.gel.impl.*;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ParserRuleContext;



public class TreeBuilder extends GelParserBaseVisitor<GelExpression> {


    public TreeBuilder(MofPackage inModel) {
        model = inModel;
        FactoryRegistry registry = FactoryRegistry.getRegistry();
                     registry.registerDefaultFactory("Integer", IntegerLiteralImpl.class);
            registry.registerDefaultFactory("Double", FloatingPointLiteralImpl.class);
                    registry.registerDefaultFactory("Boolean", BooleanLiteralImpl.class);
                        registry.registerDefaultFactory("Date", DateLiteralImpl.class);
          registry.registerDefaultFactory("String", StringLiteralImpl.class);
               }


    //========================================================================//


        @Override
public GelExpression visitGreaterThanExpression(GelParser.GreaterThanExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
        @Override
public GelExpression visitOrExpression(GelParser.OrExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
        @Override
public GelExpression visitNotExpression(GelParser.NotExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
            @Override
public GelExpression visitOppExpression(GelParser.OppExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
                    @Override
public GelExpression visitLessThanExpression(GelParser.LessThanExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
            @Override
public GelExpression visitLambdaExpression(GelParser.LambdaExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
        @Override
public GelExpression visitMultExpression(GelParser.MultExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
                @Override
public GelExpression visitAndExpression(GelParser.AndExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
            @Override
public GelExpression visitEqualExpression(GelParser.EqualExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
            @Override
public GelExpression visitGreaterOrEqualExpression(GelParser.GreaterOrEqualExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
        @Override
public GelExpression visitMinusExpression(GelParser.MinusExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
        @Override
public GelExpression visitXorExpression(GelParser.XorExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
                @Override
public GelExpression visitAddExpression(GelParser.AddExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
        @Override
public GelExpression visitLessOrEqualExpression(GelParser.LessOrEqualExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
        @Override
public GelExpression visitDifferentExpression(GelParser.DifferentExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
        @Override
public GelExpression visitDivExpression(GelParser.DivExpressionContext ctx) {
        return buildBinaryExpression(ctx);
    }
        

    //========================================================================//


                                @Override
    public GelExpression visitIntegerLiteral(GelParser.IntegerLiteralContext ctx) {
        return buildLiteral(ctx, "Integer");
    }
                @Override
    public GelExpression visitFloatingPointLiteral(GelParser.FloatingPointLiteralContext ctx) {
        return buildLiteral(ctx, "Double");
    }
                                @Override
    public GelExpression visitBooleanLiteral(GelParser.BooleanLiteralContext ctx) {
        return buildLiteral(ctx, "Boolean");
    }
                                        @Override
    public GelExpression visitDateLiteral(GelParser.DateLiteralContext ctx) {
        return buildLiteral(ctx, "Date");
    }
            @Override
    public GelExpression visitStringLiteral(GelParser.StringLiteralContext ctx) {
        return buildLiteral(ctx, "String");
    }
                        

    //========================================================================//


    protected GelExpression buildBinaryExpression(ParserRuleContext ctx) {
        int         childCount = ctx.getChildCount();
        GelExpression   result = ctx.getChild(0).accept(this);
        // run through the operands
        for (int i=2 ; i<childCount ; i+=2) {
            GelExpression   child   = ctx.getChild(i).accept(this);
            String          operator = ctx.getChild(i-1).getText();
            Operation       exp;
            try {
                exp = (Operation)FactoryRegistry.newInstance(operator);
            } catch (InstantiationException ex) {
                Logger.getLogger(TreeBuilder.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
            exp.addOperand(result);
            exp.addOperand(child);
            result = exp;
            // TODO : manage type
        }
        return result;
    }


    protected   GelExpression   buildLiteral(ParserRuleContext ctx, String inMofTypeName) {
        PackageableElement type = model.getElementByName(inMofTypeName);
        try {
            Object newInstance = FactoryRegistry.newInstance(inMofTypeName);
            Literal result = (Literal)newInstance;
            return result;
        } catch (InstantiationException ex) {
            Logger.getLogger(TreeBuilder.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }


    //========================================================================//

    private     MofPackage      model;

}


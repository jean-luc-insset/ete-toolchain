parser grammar GelParser;



options {
    tokenVocab = GelLexer;
}



//============================================================================//
// This parser is the basis for OCL parser, UEL parser and MOF-QVTo parser    //
// It handles operator priority.                                              //
// Any parser which imports this one can override any rule to customize the   //
// parser to its own needs.                                                   //
//============================================================================//





//============================================================================//
//                             E N T R Y P O I N T                            //
//============================================================================//


gelExpression :
    xorExpression
;


//============================================================================//
//                     L O G I C A L   O P E R A T I O N S                    //
//============================================================================//


xorExpression :
    (
    orExpression
    XOR
    )*
    orExpression
;


orExpression :
    (
    andExpression
    OR
    )*
    andExpression
;


andExpression :
    (
    notOrNotNotExpression
    AND
    )*
    notOrNotNotExpression
;


notOrNotNotExpression:
    notExpression
    |
    notNotExpression
;

notExpression :
    NOT
    orderExpression
;

notNotExpression :
    orderExpression
;

//============================================================================//


// In this grammar, comparisons are not associative. One cannot write
// expressions such that
//      a > b > c
orderExpression :
    (
        greaterThanExpression
        |
        greaterOrEqualExpression
        |
        lessThanExpression
        |
        lessOrEqualExpression
    )?
    compareExpression
;



greaterThanExpression :
    compareExpression
    GT
;


greaterOrEqualExpression :
    compareExpression
    GE
;


lessThanExpression :
    compareExpression
    LT
;


lessOrEqualExpression :
    compareExpression
    LE
;


// In this grammar, = and <> are not associative : one cannot write expressions
// such that
//      a = b = c
compareExpression :
    (
        equalExpression
        |
        differentExpression
    )?
    addOrSubExpression
;


equalExpression :
    addOrSubExpression
    EQUAL
;

differentExpression :
    addOrSubExpression
    NOTEQUAL
;


//============================================================================//
//                  A R I T H M E T I C   O P E R A T I O N S                 //
//============================================================================//


addOrSubExpression :
    (
        addExpression
        |
        minusExpression
    )*
    multOrDivExpression
;

addExpression :
    multOrDivExpression
    ADD
;

minusExpression :
    multOrDivExpression
    SUB
;


multOrDivExpression :
    (
        multExpression
        |
        divExpression
        |
        modExpression
    )*
    
    navExpression
;


multExpression :
    navExpression
    MUL
;

divExpression :
    navExpression
    DIV
;

modExpression:
    navExpression
    MOD
;



//============================================================================//
//                             N A V I G A T I O N                            //
//============================================================================//


navExpression :
    (
    dotExpression
    |
    arrowExpression
    )*
    stepExpression
;


dotExpression :
    stepExpression
    DOT
;

arrowExpression :
    stepExpression
    ARROW
;


stepExpression :
    atomicExpression
    (
    filterExpression
    )*
;


//----------------------------------------------------------------------------//

filterExpression :
    LBRACK
    lambdaExpression
    RBRACK
;


lambdaExpression :
    (
        variableDeclarationExpression
        (
            COMMA
            variableDeclarationExpression
        )*
        PIPE
    )?
    gelExpression
;



variableDeclarationExpression :
    Identifier
    (
        COLON
        typeExpression
    )?
;


typeExpression :
    atomicTypeExpression
    |
    sequenceTypeExpression
    |
    bagTypeExpression
    |
    setTypeExpression
    |
    orderedSetTypeExpression
;


atomicTypeExpression :
    Identifier
;

sequenceTypeExpression :
    SEQUENCE
    LPAREN
    typeExpression
    RPAREN
;

bagTypeExpression :
    BAG
    LPAREN
    typeExpression
    RPAREN
;

setTypeExpression :
    SET
    LPAREN
    typeExpression
    RPAREN
;

orderedSetTypeExpression :
    ORDERED SET
    LPAREN
    typeExpression
    RPAREN
;

//============================================================================//
//                               L I T E R A L S                              //
//============================================================================//


atomicExpression :
    oppExpression
    |
    identifier
    |
    literal
;

oppExpression :
    SUB
    atomicExpression
;

literal :
    integerLiteral
    |
    floatingPointLiteral
    |
    booleanLiteral
    |
    dateLiteral
    |
    characterLiteral
    |
    stringLiteral
    |
    nullLiteral
;




identifier           : Identifier;
integerLiteral       : IntegerLiteral;
floatingPointLiteral : FloatingPointLiteral;
booleanLiteral       : BooleanLiteral;
dateLiteral          : DateLiteral;
characterLiteral     : CharacterLiteral;
stringLiteral        : StringLiteral;
nullLiteral          : NullLiteral;


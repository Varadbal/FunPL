/*
 * generated by Xtext 2.14.0
 */
package xyz.varad.funpl.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.common.services.TerminalsGrammarAccess;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class FunPLGrammarAccess extends AbstractGrammarElementFinder {
	
	public class FunProgramElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "xyz.varad.funpl.FunPL.FunProgram");
		private final Assignment cElementsAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cElementsAbstractElementParserRuleCall_0 = (RuleCall)cElementsAssignment.eContents().get(0);
		
		//FunProgram:
		//	elements+=AbstractElement*;
		@Override public ParserRule getRule() { return rule; }
		
		//elements+=AbstractElement*
		public Assignment getElementsAssignment() { return cElementsAssignment; }
		
		//AbstractElement
		public RuleCall getElementsAbstractElementParserRuleCall_0() { return cElementsAbstractElementParserRuleCall_0; }
	}
	public class AbstractElementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "xyz.varad.funpl.FunPL.AbstractElement");
		private final RuleCall cDefinitionParserRuleCall = (RuleCall)rule.eContents().get(1);
		
		//AbstractElement:
		//	Definition;
		@Override public ParserRule getRule() { return rule; }
		
		//Definition
		public RuleCall getDefinitionParserRuleCall() { return cDefinitionParserRuleCall; }
	}
	public class DefinitionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "xyz.varad.funpl.FunPL.Definition");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cValueParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cFunctionParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//Definition:
		//	Value | Function;
		@Override public ParserRule getRule() { return rule; }
		
		//Value | Function
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//Value
		public RuleCall getValueParserRuleCall_0() { return cValueParserRuleCall_0; }
		
		//Function
		public RuleCall getFunctionParserRuleCall_1() { return cFunctionParserRuleCall_1; }
	}
	public class ValueElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "xyz.varad.funpl.FunPL.Value");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Alternatives cAlternatives_0 = (Alternatives)cGroup.eContents().get(0);
		private final Assignment cIsConstAssignment_0_0 = (Assignment)cAlternatives_0.eContents().get(0);
		private final Keyword cIsConstConstKeyword_0_0_0 = (Keyword)cIsConstAssignment_0_0.eContents().get(0);
		private final Keyword cVarKeyword_0_1 = (Keyword)cAlternatives_0.eContents().get(1);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cEqualsSignKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cExpressionAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cExpressionExpressionParserRuleCall_2_1_0 = (RuleCall)cExpressionAssignment_2_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//Value:
		//	(isConst?='const' | 'var') name=ID ('=' expression=Expression)? ';';
		@Override public ParserRule getRule() { return rule; }
		
		//(isConst?='const' | 'var') name=ID ('=' expression=Expression)? ';'
		public Group getGroup() { return cGroup; }
		
		////TODO force const definition - validation
		////TODO type
		//isConst?='const' | 'var'
		public Alternatives getAlternatives_0() { return cAlternatives_0; }
		
		//isConst?='const'
		public Assignment getIsConstAssignment_0_0() { return cIsConstAssignment_0_0; }
		
		//'const'
		public Keyword getIsConstConstKeyword_0_0_0() { return cIsConstConstKeyword_0_0_0; }
		
		//'var'
		public Keyword getVarKeyword_0_1() { return cVarKeyword_0_1; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
		
		//('=' expression=Expression)?
		public Group getGroup_2() { return cGroup_2; }
		
		//'='
		public Keyword getEqualsSignKeyword_2_0() { return cEqualsSignKeyword_2_0; }
		
		//expression=Expression
		public Assignment getExpressionAssignment_2_1() { return cExpressionAssignment_2_1; }
		
		//Expression
		public RuleCall getExpressionExpressionParserRuleCall_2_1_0() { return cExpressionExpressionParserRuleCall_2_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_3() { return cSemicolonKeyword_3; }
	}
	public class FunctionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "xyz.varad.funpl.FunPL.Function");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cFunctionKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cParamsAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cParamsFunctionParamParserRuleCall_3_0_0 = (RuleCall)cParamsAssignment_3_0.eContents().get(0);
		private final Group cGroup_3_1 = (Group)cGroup_3.eContents().get(1);
		private final Keyword cCommaKeyword_3_1_0 = (Keyword)cGroup_3_1.eContents().get(0);
		private final Assignment cParamsAssignment_3_1_1 = (Assignment)cGroup_3_1.eContents().get(1);
		private final RuleCall cParamsFunctionParamParserRuleCall_3_1_1_0 = (RuleCall)cParamsAssignment_3_1_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Assignment cBodyAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cBodyBlockParserRuleCall_5_0 = (RuleCall)cBodyAssignment_5.eContents().get(0);
		
		//Function:
		//	'function' name=ID '(' (params+=FunctionParam (',' params+=FunctionParam)*)? ')' body=Block;
		@Override public ParserRule getRule() { return rule; }
		
		//'function' name=ID '(' (params+=FunctionParam (',' params+=FunctionParam)*)? ')' body=Block
		public Group getGroup() { return cGroup; }
		
		//'function'
		public Keyword getFunctionKeyword_0() { return cFunctionKeyword_0; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
		
		//'('
		public Keyword getLeftParenthesisKeyword_2() { return cLeftParenthesisKeyword_2; }
		
		//(params+=FunctionParam (',' params+=FunctionParam)*)?
		public Group getGroup_3() { return cGroup_3; }
		
		//params+=FunctionParam
		public Assignment getParamsAssignment_3_0() { return cParamsAssignment_3_0; }
		
		//FunctionParam
		public RuleCall getParamsFunctionParamParserRuleCall_3_0_0() { return cParamsFunctionParamParserRuleCall_3_0_0; }
		
		//(',' params+=FunctionParam)*
		public Group getGroup_3_1() { return cGroup_3_1; }
		
		//','
		public Keyword getCommaKeyword_3_1_0() { return cCommaKeyword_3_1_0; }
		
		//params+=FunctionParam
		public Assignment getParamsAssignment_3_1_1() { return cParamsAssignment_3_1_1; }
		
		//FunctionParam
		public RuleCall getParamsFunctionParamParserRuleCall_3_1_1_0() { return cParamsFunctionParamParserRuleCall_3_1_1_0; }
		
		//')'
		public Keyword getRightParenthesisKeyword_4() { return cRightParenthesisKeyword_4; }
		
		//body=Block
		public Assignment getBodyAssignment_5() { return cBodyAssignment_5; }
		
		//Block
		public RuleCall getBodyBlockParserRuleCall_5_0() { return cBodyBlockParserRuleCall_5_0; }
	}
	public class FunctionParamElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "xyz.varad.funpl.FunPL.FunctionParam");
		private final Assignment cNameAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_0 = (RuleCall)cNameAssignment.eContents().get(0);
		
		//FunctionParam:
		//	Name=ID;
		@Override public ParserRule getRule() { return rule; }
		
		//Name=ID
		public Assignment getNameAssignment() { return cNameAssignment; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_0() { return cNameIDTerminalRuleCall_0; }
	}
	public class BlockElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "xyz.varad.funpl.FunPL.Block");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cLeftCurlyBracketKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Action cBlockAction_1 = (Action)cGroup.eContents().get(1);
		private final Assignment cStatementsAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cStatementsStatementParserRuleCall_2_0 = (RuleCall)cStatementsAssignment_2.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//Block:
		//	'{' {Block} statements+=Statement*
		//	'}';
		@Override public ParserRule getRule() { return rule; }
		
		//'{' {Block} statements+=Statement* '}'
		public Group getGroup() { return cGroup; }
		
		//'{'
		public Keyword getLeftCurlyBracketKeyword_0() { return cLeftCurlyBracketKeyword_0; }
		
		//{Block}
		public Action getBlockAction_1() { return cBlockAction_1; }
		
		//statements+=Statement*
		public Assignment getStatementsAssignment_2() { return cStatementsAssignment_2; }
		
		//Statement
		public RuleCall getStatementsStatementParserRuleCall_2_0() { return cStatementsStatementParserRuleCall_2_0; }
		
		//'}'
		public Keyword getRightCurlyBracketKeyword_3() { return cRightCurlyBracketKeyword_3; }
	}
	public class StatementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "xyz.varad.funpl.FunPL.Statement");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cValueParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final Group cGroup_1 = (Group)cAlternatives.eContents().get(1);
		private final RuleCall cExpressionParserRuleCall_1_0 = (RuleCall)cGroup_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		
		//Statement:
		//	Value | Expression ';';
		@Override public ParserRule getRule() { return rule; }
		
		//Value | Expression ';'
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//Value
		public RuleCall getValueParserRuleCall_0() { return cValueParserRuleCall_0; }
		
		//Expression ';'
		public Group getGroup_1() { return cGroup_1; }
		
		//Expression
		public RuleCall getExpressionParserRuleCall_1_0() { return cExpressionParserRuleCall_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_1_1() { return cSemicolonKeyword_1_1; }
	}
	public class ExpressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "xyz.varad.funpl.FunPL.Expression");
		private final RuleCall cAssignmentParserRuleCall = (RuleCall)rule.eContents().get(1);
		
		//Expression:
		//	Assignment;
		@Override public ParserRule getRule() { return rule; }
		
		//Assignment
		public RuleCall getAssignmentParserRuleCall() { return cAssignmentParserRuleCall; }
	}
	public class AssignmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "xyz.varad.funpl.FunPL.Assignment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cPlusParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Action cAssignmentLeftAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		private final Assignment cRightAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cRightExpressionParserRuleCall_1_2_0 = (RuleCall)cRightAssignment_1_2.eContents().get(0);
		
		//Assignment Expression:
		//	Plus ({Assignment.left=current} '=' right=Expression)?;
		@Override public ParserRule getRule() { return rule; }
		
		//Plus ({Assignment.left=current} '=' right=Expression)?
		public Group getGroup() { return cGroup; }
		
		//Plus
		public RuleCall getPlusParserRuleCall_0() { return cPlusParserRuleCall_0; }
		
		//({Assignment.left=current} '=' right=Expression)?
		public Group getGroup_1() { return cGroup_1; }
		
		//{Assignment.left=current}
		public Action getAssignmentLeftAction_1_0() { return cAssignmentLeftAction_1_0; }
		
		//'='
		public Keyword getEqualsSignKeyword_1_1() { return cEqualsSignKeyword_1_1; }
		
		//right=Expression
		public Assignment getRightAssignment_1_2() { return cRightAssignment_1_2; }
		
		//Expression
		public RuleCall getRightExpressionParserRuleCall_1_2_0() { return cRightExpressionParserRuleCall_1_2_0; }
	}
	public class PlusElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "xyz.varad.funpl.FunPL.Plus");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cPrimaryExpressionParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Action cPlusLeftAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Keyword cPlusSignKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		private final Assignment cRightAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cRightPrimaryExpressionParserRuleCall_1_2_0 = (RuleCall)cRightAssignment_1_2.eContents().get(0);
		
		//Plus Expression:
		//	PrimaryExpression ({Plus.left=current} '+' right=PrimaryExpression)*;
		@Override public ParserRule getRule() { return rule; }
		
		//PrimaryExpression ({Plus.left=current} '+' right=PrimaryExpression)*
		public Group getGroup() { return cGroup; }
		
		//PrimaryExpression
		public RuleCall getPrimaryExpressionParserRuleCall_0() { return cPrimaryExpressionParserRuleCall_0; }
		
		//({Plus.left=current} '+' right=PrimaryExpression)*
		public Group getGroup_1() { return cGroup_1; }
		
		//{Plus.left=current}
		public Action getPlusLeftAction_1_0() { return cPlusLeftAction_1_0; }
		
		//'+'
		public Keyword getPlusSignKeyword_1_1() { return cPlusSignKeyword_1_1; }
		
		//right=PrimaryExpression
		public Assignment getRightAssignment_1_2() { return cRightAssignment_1_2; }
		
		//PrimaryExpression
		public RuleCall getRightPrimaryExpressionParserRuleCall_1_2_0() { return cRightPrimaryExpressionParserRuleCall_1_2_0; }
	}
	public class PrimaryExpressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "xyz.varad.funpl.FunPL.PrimaryExpression");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Group cGroup_0 = (Group)cAlternatives.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_0_0 = (Keyword)cGroup_0.eContents().get(0);
		private final RuleCall cExpressionParserRuleCall_0_1 = (RuleCall)cGroup_0.eContents().get(1);
		private final Keyword cRightParenthesisKeyword_0_2 = (Keyword)cGroup_0.eContents().get(2);
		private final RuleCall cFunctionCallParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cTerminalExpressionParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		
		//PrimaryExpression Expression:
		//	'(' Expression ')' | FunctionCall | TerminalExpression;
		@Override public ParserRule getRule() { return rule; }
		
		//'(' Expression ')' | FunctionCall | TerminalExpression
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//'(' Expression ')'
		public Group getGroup_0() { return cGroup_0; }
		
		//'('
		public Keyword getLeftParenthesisKeyword_0_0() { return cLeftParenthesisKeyword_0_0; }
		
		//Expression
		public RuleCall getExpressionParserRuleCall_0_1() { return cExpressionParserRuleCall_0_1; }
		
		//')'
		public Keyword getRightParenthesisKeyword_0_2() { return cRightParenthesisKeyword_0_2; }
		
		//FunctionCall
		public RuleCall getFunctionCallParserRuleCall_1() { return cFunctionCallParserRuleCall_1; }
		
		//TerminalExpression
		public RuleCall getTerminalExpressionParserRuleCall_2() { return cTerminalExpressionParserRuleCall_2; }
	}
	public class FunctionCallElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "xyz.varad.funpl.FunPL.FunctionCall");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cFunctionCallAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cFunctionAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final CrossReference cFunctionFunctionCrossReference_1_0 = (CrossReference)cFunctionAssignment_1.eContents().get(0);
		private final RuleCall cFunctionFunctionIDTerminalRuleCall_1_0_1 = (RuleCall)cFunctionFunctionCrossReference_1_0.eContents().get(1);
		private final Keyword cLeftParenthesisKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cArgsAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cArgsExpressionParserRuleCall_3_0_0 = (RuleCall)cArgsAssignment_3_0.eContents().get(0);
		private final Group cGroup_3_1 = (Group)cGroup_3.eContents().get(1);
		private final Keyword cCommaKeyword_3_1_0 = (Keyword)cGroup_3_1.eContents().get(0);
		private final Assignment cArgsAssignment_3_1_1 = (Assignment)cGroup_3_1.eContents().get(1);
		private final RuleCall cArgsExpressionParserRuleCall_3_1_1_0 = (RuleCall)cArgsAssignment_3_1_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_4 = (Keyword)cGroup.eContents().get(4);
		
		//FunctionCall Expression:
		//	{FunctionCall} function=[Function] '(' (args+=Expression (',' args+=Expression)*)? ')';
		@Override public ParserRule getRule() { return rule; }
		
		//{FunctionCall} function=[Function] '(' (args+=Expression (',' args+=Expression)*)? ')'
		public Group getGroup() { return cGroup; }
		
		//{FunctionCall}
		public Action getFunctionCallAction_0() { return cFunctionCallAction_0; }
		
		//function=[Function]
		public Assignment getFunctionAssignment_1() { return cFunctionAssignment_1; }
		
		//[Function]
		public CrossReference getFunctionFunctionCrossReference_1_0() { return cFunctionFunctionCrossReference_1_0; }
		
		//ID
		public RuleCall getFunctionFunctionIDTerminalRuleCall_1_0_1() { return cFunctionFunctionIDTerminalRuleCall_1_0_1; }
		
		//'('
		public Keyword getLeftParenthesisKeyword_2() { return cLeftParenthesisKeyword_2; }
		
		//(args+=Expression (',' args+=Expression)*)?
		public Group getGroup_3() { return cGroup_3; }
		
		//args+=Expression
		public Assignment getArgsAssignment_3_0() { return cArgsAssignment_3_0; }
		
		//Expression
		public RuleCall getArgsExpressionParserRuleCall_3_0_0() { return cArgsExpressionParserRuleCall_3_0_0; }
		
		//(',' args+=Expression)*
		public Group getGroup_3_1() { return cGroup_3_1; }
		
		//','
		public Keyword getCommaKeyword_3_1_0() { return cCommaKeyword_3_1_0; }
		
		//args+=Expression
		public Assignment getArgsAssignment_3_1_1() { return cArgsAssignment_3_1_1; }
		
		//Expression
		public RuleCall getArgsExpressionParserRuleCall_3_1_1_0() { return cArgsExpressionParserRuleCall_3_1_1_0; }
		
		//')'
		public Keyword getRightParenthesisKeyword_4() { return cRightParenthesisKeyword_4; }
	}
	public class TerminalExpressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "xyz.varad.funpl.FunPL.TerminalExpression");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Group cGroup_0 = (Group)cAlternatives.eContents().get(0);
		private final Action cIntConstantAction_0_0 = (Action)cGroup_0.eContents().get(0);
		private final Assignment cValueAssignment_0_1 = (Assignment)cGroup_0.eContents().get(1);
		private final RuleCall cValueINTTerminalRuleCall_0_1_0 = (RuleCall)cValueAssignment_0_1.eContents().get(0);
		private final Group cGroup_1 = (Group)cAlternatives.eContents().get(1);
		private final Action cStringConstantAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Assignment cValueAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cValueSTRINGTerminalRuleCall_1_1_0 = (RuleCall)cValueAssignment_1_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cAlternatives.eContents().get(2);
		private final Action cBoolConstantAction_2_0 = (Action)cGroup_2.eContents().get(0);
		private final Assignment cValueAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final Alternatives cValueAlternatives_2_1_0 = (Alternatives)cValueAssignment_2_1.eContents().get(0);
		private final Keyword cValueTrueKeyword_2_1_0_0 = (Keyword)cValueAlternatives_2_1_0.eContents().get(0);
		private final Keyword cValueFalseKeyword_2_1_0_1 = (Keyword)cValueAlternatives_2_1_0.eContents().get(1);
		private final Group cGroup_3 = (Group)cAlternatives.eContents().get(3);
		private final Action cValueRefAction_3_0 = (Action)cGroup_3.eContents().get(0);
		private final Assignment cVariableAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final CrossReference cVariableValueCrossReference_3_1_0 = (CrossReference)cVariableAssignment_3_1.eContents().get(0);
		private final RuleCall cVariableValueIDTerminalRuleCall_3_1_0_1 = (RuleCall)cVariableValueCrossReference_3_1_0.eContents().get(1);
		
		//TerminalExpression:
		//	{IntConstant} value=INT | {StringConstant} value=STRING | {BoolConstant} value=('true' | 'false') | {ValueRef}
		//	variable=[Value];
		@Override public ParserRule getRule() { return rule; }
		
		//{IntConstant} value=INT | {StringConstant} value=STRING | {BoolConstant} value=('true' | 'false') | {ValueRef}
		//variable=[Value]
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//{IntConstant} value=INT
		public Group getGroup_0() { return cGroup_0; }
		
		//{IntConstant}
		public Action getIntConstantAction_0_0() { return cIntConstantAction_0_0; }
		
		//value=INT
		public Assignment getValueAssignment_0_1() { return cValueAssignment_0_1; }
		
		//INT
		public RuleCall getValueINTTerminalRuleCall_0_1_0() { return cValueINTTerminalRuleCall_0_1_0; }
		
		//{StringConstant} value=STRING
		public Group getGroup_1() { return cGroup_1; }
		
		//{StringConstant}
		public Action getStringConstantAction_1_0() { return cStringConstantAction_1_0; }
		
		//value=STRING
		public Assignment getValueAssignment_1_1() { return cValueAssignment_1_1; }
		
		//STRING
		public RuleCall getValueSTRINGTerminalRuleCall_1_1_0() { return cValueSTRINGTerminalRuleCall_1_1_0; }
		
		//{BoolConstant} value=('true' | 'false')
		public Group getGroup_2() { return cGroup_2; }
		
		//{BoolConstant}
		public Action getBoolConstantAction_2_0() { return cBoolConstantAction_2_0; }
		
		//value=('true' | 'false')
		public Assignment getValueAssignment_2_1() { return cValueAssignment_2_1; }
		
		//('true' | 'false')
		public Alternatives getValueAlternatives_2_1_0() { return cValueAlternatives_2_1_0; }
		
		//'true'
		public Keyword getValueTrueKeyword_2_1_0_0() { return cValueTrueKeyword_2_1_0_0; }
		
		//'false'
		public Keyword getValueFalseKeyword_2_1_0_1() { return cValueFalseKeyword_2_1_0_1; }
		
		//{ValueRef} variable=[Value]
		public Group getGroup_3() { return cGroup_3; }
		
		//{ValueRef}
		public Action getValueRefAction_3_0() { return cValueRefAction_3_0; }
		
		//variable=[Value]
		public Assignment getVariableAssignment_3_1() { return cVariableAssignment_3_1; }
		
		//[Value]
		public CrossReference getVariableValueCrossReference_3_1_0() { return cVariableValueCrossReference_3_1_0; }
		
		//ID
		public RuleCall getVariableValueIDTerminalRuleCall_3_1_0_1() { return cVariableValueIDTerminalRuleCall_3_1_0_1; }
	}
	
	
	private final FunProgramElements pFunProgram;
	private final AbstractElementElements pAbstractElement;
	private final DefinitionElements pDefinition;
	private final ValueElements pValue;
	private final FunctionElements pFunction;
	private final FunctionParamElements pFunctionParam;
	private final BlockElements pBlock;
	private final StatementElements pStatement;
	private final ExpressionElements pExpression;
	private final AssignmentElements pAssignment;
	private final PlusElements pPlus;
	private final PrimaryExpressionElements pPrimaryExpression;
	private final FunctionCallElements pFunctionCall;
	private final TerminalExpressionElements pTerminalExpression;
	
	private final Grammar grammar;
	
	private final TerminalsGrammarAccess gaTerminals;

	@Inject
	public FunPLGrammarAccess(GrammarProvider grammarProvider,
			TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
		this.pFunProgram = new FunProgramElements();
		this.pAbstractElement = new AbstractElementElements();
		this.pDefinition = new DefinitionElements();
		this.pValue = new ValueElements();
		this.pFunction = new FunctionElements();
		this.pFunctionParam = new FunctionParamElements();
		this.pBlock = new BlockElements();
		this.pStatement = new StatementElements();
		this.pExpression = new ExpressionElements();
		this.pAssignment = new AssignmentElements();
		this.pPlus = new PlusElements();
		this.pPrimaryExpression = new PrimaryExpressionElements();
		this.pFunctionCall = new FunctionCallElements();
		this.pTerminalExpression = new TerminalExpressionElements();
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("xyz.varad.funpl.FunPL".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	
	
	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//FunProgram:
	//	elements+=AbstractElement*;
	public FunProgramElements getFunProgramAccess() {
		return pFunProgram;
	}
	
	public ParserRule getFunProgramRule() {
		return getFunProgramAccess().getRule();
	}
	
	//AbstractElement:
	//	Definition;
	public AbstractElementElements getAbstractElementAccess() {
		return pAbstractElement;
	}
	
	public ParserRule getAbstractElementRule() {
		return getAbstractElementAccess().getRule();
	}
	
	//Definition:
	//	Value | Function;
	public DefinitionElements getDefinitionAccess() {
		return pDefinition;
	}
	
	public ParserRule getDefinitionRule() {
		return getDefinitionAccess().getRule();
	}
	
	//Value:
	//	(isConst?='const' | 'var') name=ID ('=' expression=Expression)? ';';
	public ValueElements getValueAccess() {
		return pValue;
	}
	
	public ParserRule getValueRule() {
		return getValueAccess().getRule();
	}
	
	//Function:
	//	'function' name=ID '(' (params+=FunctionParam (',' params+=FunctionParam)*)? ')' body=Block;
	public FunctionElements getFunctionAccess() {
		return pFunction;
	}
	
	public ParserRule getFunctionRule() {
		return getFunctionAccess().getRule();
	}
	
	//FunctionParam:
	//	Name=ID;
	public FunctionParamElements getFunctionParamAccess() {
		return pFunctionParam;
	}
	
	public ParserRule getFunctionParamRule() {
		return getFunctionParamAccess().getRule();
	}
	
	//Block:
	//	'{' {Block} statements+=Statement*
	//	'}';
	public BlockElements getBlockAccess() {
		return pBlock;
	}
	
	public ParserRule getBlockRule() {
		return getBlockAccess().getRule();
	}
	
	//Statement:
	//	Value | Expression ';';
	public StatementElements getStatementAccess() {
		return pStatement;
	}
	
	public ParserRule getStatementRule() {
		return getStatementAccess().getRule();
	}
	
	//Expression:
	//	Assignment;
	public ExpressionElements getExpressionAccess() {
		return pExpression;
	}
	
	public ParserRule getExpressionRule() {
		return getExpressionAccess().getRule();
	}
	
	//Assignment Expression:
	//	Plus ({Assignment.left=current} '=' right=Expression)?;
	public AssignmentElements getAssignmentAccess() {
		return pAssignment;
	}
	
	public ParserRule getAssignmentRule() {
		return getAssignmentAccess().getRule();
	}
	
	//Plus Expression:
	//	PrimaryExpression ({Plus.left=current} '+' right=PrimaryExpression)*;
	public PlusElements getPlusAccess() {
		return pPlus;
	}
	
	public ParserRule getPlusRule() {
		return getPlusAccess().getRule();
	}
	
	//PrimaryExpression Expression:
	//	'(' Expression ')' | FunctionCall | TerminalExpression;
	public PrimaryExpressionElements getPrimaryExpressionAccess() {
		return pPrimaryExpression;
	}
	
	public ParserRule getPrimaryExpressionRule() {
		return getPrimaryExpressionAccess().getRule();
	}
	
	//FunctionCall Expression:
	//	{FunctionCall} function=[Function] '(' (args+=Expression (',' args+=Expression)*)? ')';
	public FunctionCallElements getFunctionCallAccess() {
		return pFunctionCall;
	}
	
	public ParserRule getFunctionCallRule() {
		return getFunctionCallAccess().getRule();
	}
	
	//TerminalExpression:
	//	{IntConstant} value=INT | {StringConstant} value=STRING | {BoolConstant} value=('true' | 'false') | {ValueRef}
	//	variable=[Value];
	public TerminalExpressionElements getTerminalExpressionAccess() {
		return pTerminalExpression;
	}
	
	public ParserRule getTerminalExpressionRule() {
		return getTerminalExpressionAccess().getRule();
	}
	
	//terminal ID:
	//	'^'? ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	}
	
	//terminal INT returns ecore::EInt:
	//	'0'..'9'+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	}
	
	//terminal STRING:
	//	'"' ('\\' . | !('\\' | '"'))* '"' |
	//	"'" ('\\' . | !('\\' | "'"))* "'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	}
	
	//terminal ML_COMMENT:
	//	'/*'->'*/';
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	}
	
	//terminal SL_COMMENT:
	//	'//' !('\n' | '\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	}
	
	//terminal WS:
	//	' ' | '\t' | '\r' | '\n'+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	}
	
	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	}
}

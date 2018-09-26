package xyz.varad.funpl.typing;

import java.util.List;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import xyz.varad.funpl.funPL.AssignmentExpression;
import xyz.varad.funpl.funPL.BooleanLiteralExpression;
import xyz.varad.funpl.funPL.BooleanTypeDefinition;
import xyz.varad.funpl.funPL.Declaration;
import xyz.varad.funpl.funPL.Expression;
import xyz.varad.funpl.funPL.FunPLFactory;
import xyz.varad.funpl.funPL.FunctionDeclaration;
import xyz.varad.funpl.funPL.IntegerLiteralExpression;
import xyz.varad.funpl.funPL.IntegerTypeDefinition;
import xyz.varad.funpl.funPL.PlusExpression;
import xyz.varad.funpl.funPL.ReferenceExpression;
import xyz.varad.funpl.funPL.ReturnStatement;
import xyz.varad.funpl.funPL.Type;
import xyz.varad.funpl.funPL.TypeInferredElement;
import xyz.varad.funpl.funPL.ValueDeclaration;
import xyz.varad.funpl.funPL.VoidTypeDefinition;
import xyz.varad.funpl.util.FunPLModelUtil;

@SuppressWarnings("all")
public class FunPLTypeComputer {
  private final static FunPLFactory factory = FunPLFactory.eINSTANCE;
  
  public static Type computeDeepType(final TypeInferredElement e) {
    Type _switchResult = null;
    boolean _matched = false;
    if (e instanceof Declaration) {
      _matched=true;
      Type _switchResult_1 = null;
      boolean _matched_1 = false;
      if (e instanceof ValueDeclaration) {
        _matched_1=true;
        Type _xifexpression = null;
        boolean _isSetExpression = ((ValueDeclaration)e).isSetExpression();
        if (_isSetExpression) {
          _xifexpression = ((ValueDeclaration)e).getExpression().getReturnType();
        } else {
          Type _xifexpression_1 = null;
          boolean _isSetDeclaredType = ((ValueDeclaration)e).isSetDeclaredType();
          if (_isSetDeclaredType) {
            _xifexpression_1 = ((ValueDeclaration)e).getDeclaredType();
          } else {
            _xifexpression_1 = FunPLTypeComputer.getVoid();
          }
          _xifexpression = _xifexpression_1;
        }
        _switchResult_1 = _xifexpression;
      }
      if (!_matched_1) {
        if (e instanceof FunctionDeclaration) {
          _matched_1=true;
          Type _xblockexpression = null;
          {
            final List<ReturnStatement> rs = FunPLModelUtil.returnStatements(((FunctionDeclaration)e));
            Type _xifexpression = null;
            boolean _isEmpty = rs.isEmpty();
            boolean _not = (!_isEmpty);
            if (_not) {
              _xifexpression = IterableExtensions.<ReturnStatement>head(rs).getReturnType();
            } else {
              Type _xifexpression_1 = null;
              boolean _isSetDeclaredType = ((FunctionDeclaration)e).isSetDeclaredType();
              if (_isSetDeclaredType) {
                _xifexpression_1 = ((FunctionDeclaration)e).getDeclaredType();
              } else {
                _xifexpression_1 = FunPLTypeComputer.getVoid();
              }
              _xifexpression = _xifexpression_1;
            }
            _xblockexpression = _xifexpression;
          }
          _switchResult_1 = _xblockexpression;
        }
      }
      _switchResult = _switchResult_1;
    }
    if (!_matched) {
      if (e instanceof Expression) {
        _matched=true;
        Type _switchResult_1 = null;
        boolean _matched_1 = false;
        if (e instanceof ReferenceExpression) {
          _matched_1=true;
          _switchResult_1 = ((ReferenceExpression)e).getDeclaration().getReturnType();
        }
        if (!_matched_1) {
          if (e instanceof PlusExpression) {
            _matched_1=true;
            _switchResult_1 = FunPLTypeComputer.getInteger();
          }
        }
        if (!_matched_1) {
          if (e instanceof AssignmentExpression) {
            _matched_1=true;
            _switchResult_1 = ((AssignmentExpression)e).getLeft().getReturnType();
          }
        }
        if (!_matched_1) {
          if (e instanceof IntegerLiteralExpression) {
            _matched_1=true;
            _switchResult_1 = FunPLTypeComputer.getInteger();
          }
        }
        if (!_matched_1) {
          if (e instanceof BooleanLiteralExpression) {
            _matched_1=true;
            _switchResult_1 = FunPLTypeComputer.getBoolean();
          }
        }
        _switchResult = _switchResult_1;
      }
    }
    if (!_matched) {
      if (e instanceof ReturnStatement) {
        _matched=true;
        boolean _isSetExpression = ((ReturnStatement)e).isSetExpression();
        if (_isSetExpression) {
          return ((ReturnStatement)e).getExpression().getReturnType();
        } else {
          return FunPLTypeComputer.getVoid();
        }
      }
    }
    if (!_matched) {
      _switchResult = FunPLTypeComputer.getVoid();
    }
    return _switchResult;
  }
  
  private static VoidTypeDefinition getVoid() {
    return FunPLTypeComputer.factory.createVoidTypeDefinition();
  }
  
  private static IntegerTypeDefinition getInteger() {
    return FunPLTypeComputer.factory.createIntegerTypeDefinition();
  }
  
  private static BooleanTypeDefinition getBoolean() {
    return FunPLTypeComputer.factory.createBooleanTypeDefinition();
  }
}

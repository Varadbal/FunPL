package xyz.varad.funpl.util;

import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import xyz.varad.funpl.funPL.AbstractElement;
import xyz.varad.funpl.funPL.ArgumentList;
import xyz.varad.funpl.funPL.AssignmentExpression;
import xyz.varad.funpl.funPL.BasicTypeDefinition;
import xyz.varad.funpl.funPL.BinaryExpression;
import xyz.varad.funpl.funPL.Block;
import xyz.varad.funpl.funPL.BooleanLiteralExpression;
import xyz.varad.funpl.funPL.BooleanTypeDefinition;
import xyz.varad.funpl.funPL.Expression;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.funPL.FunctionDeclaration;
import xyz.varad.funpl.funPL.IntegerLiteralExpression;
import xyz.varad.funpl.funPL.IntegerTypeDefinition;
import xyz.varad.funpl.funPL.LiteralExpression;
import xyz.varad.funpl.funPL.ParameterList;
import xyz.varad.funpl.funPL.PlusExpression;
import xyz.varad.funpl.funPL.ReferenceExpression;
import xyz.varad.funpl.funPL.ReturnStatement;
import xyz.varad.funpl.funPL.Statement;
import xyz.varad.funpl.funPL.TypeDefinition;
import xyz.varad.funpl.funPL.ValueDeclaration;
import xyz.varad.funpl.funPL.VoidTypeDefinition;

@SuppressWarnings("all")
public class FunPLNativeStringUtil {
  protected static String _stringRepr(final FunProgram e) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<AbstractElement> _elements = e.getElements();
      for(final AbstractElement s : _elements) {
        String _stringRepr = FunPLNativeStringUtil.stringRepr(s);
        _builder.append(_stringRepr);
        {
          if ((s instanceof ValueDeclaration)) {
            _builder.append(";");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }
  
  protected static String _stringRepr(final ValueDeclaration e) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isConstant = e.isConstant();
      if (_isConstant) {
        _builder.append("const ");
      } else {
        _builder.append("var ");
      }
    }
    {
      boolean _isSetDeclaredType = e.isSetDeclaredType();
      if (_isSetDeclaredType) {
        String _stringRepr = FunPLNativeStringUtil.stringRepr(e.getDeclaredType());
        _builder.append(_stringRepr);
        _builder.append(" ");
      }
    }
    String _name = e.getName();
    _builder.append(_name);
    {
      boolean _isSetExpression = e.isSetExpression();
      if (_isSetExpression) {
        _builder.append(" = ");
        String _stringRepr_1 = FunPLNativeStringUtil.stringRepr(e.getExpression());
        _builder.append(_stringRepr_1);
      }
    }
    return _builder.toString();
  }
  
  protected static String _stringRepr(final FunctionDeclaration e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("function ");
    {
      boolean _isSetDeclaredType = e.isSetDeclaredType();
      if (_isSetDeclaredType) {
        String _stringRepr = FunPLNativeStringUtil.stringRepr(e.getDeclaredType());
        _builder.append(_stringRepr);
        _builder.append(" ");
      }
    }
    String _name = e.getName();
    _builder.append(_name);
    String _stringRepr_1 = FunPLNativeStringUtil.stringRepr(e.getParameterList());
    _builder.append(_stringRepr_1);
    String _stringRepr_2 = FunPLNativeStringUtil.stringRepr(e.getBody());
    _builder.append(_stringRepr_2);
    return _builder.toString();
  }
  
  protected static String _stringRepr(final ParameterList e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("(");
    {
      EList<ValueDeclaration> _parameters = e.getParameters();
      for(final ValueDeclaration p : _parameters) {
        String _stringRepr = FunPLNativeStringUtil.stringRepr(p.getDeclaredType());
        _builder.append(_stringRepr);
        _builder.append(" ");
        String _name = p.getName();
        _builder.append(_name);
        {
          ValueDeclaration _last = IterableExtensions.<ValueDeclaration>last(e.getParameters());
          boolean _tripleNotEquals = (p != _last);
          if (_tripleNotEquals) {
            _builder.append(", ");
          }
        }
      }
    }
    _builder.append(")");
    return _builder.toString();
  }
  
  protected static String _stringRepr(final Block e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    _builder.newLine();
    {
      EList<Statement> _statements = e.getStatements();
      for(final Statement s : _statements) {
        _builder.append("\t");
        String _stringRepr = FunPLNativeStringUtil.stringRepr(s);
        _builder.append(_stringRepr, "\t");
        {
          if ((((s instanceof ValueDeclaration) || (s instanceof Expression)) || (s instanceof ReturnStatement))) {
            _builder.append(";");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  protected static String _stringRepr(final ReturnStatement e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("return");
    {
      boolean _isSetExpression = e.isSetExpression();
      if (_isSetExpression) {
        _builder.append(" ");
        String _stringRepr = FunPLNativeStringUtil.stringRepr(e.getExpression());
        _builder.append(_stringRepr);
      }
    }
    return _builder.toString();
  }
  
  protected static String _stringRepr(final ArgumentList e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("(");
    {
      EList<Expression> _arguments = e.getArguments();
      for(final Expression a : _arguments) {
        String _stringRepr = FunPLNativeStringUtil.stringRepr(a);
        _builder.append(_stringRepr);
        {
          Expression _last = IterableExtensions.<Expression>last(e.getArguments());
          boolean _tripleNotEquals = (a != _last);
          if (_tripleNotEquals) {
            _builder.append(", ");
          }
        }
      }
    }
    _builder.append(")");
    return _builder.toString();
  }
  
  protected static String _stringRepr(final Expression e) {
    String _switchResult = null;
    boolean _matched = false;
    if (e instanceof BinaryExpression) {
      _matched=true;
      String _switchResult_1 = null;
      boolean _matched_1 = false;
      if (e instanceof AssignmentExpression) {
        _matched_1=true;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("(");
        String _stringRepr = FunPLNativeStringUtil.stringRepr(((AssignmentExpression)e).getLeft());
        _builder.append(_stringRepr);
        _builder.append(" = ");
        String _stringRepr_1 = FunPLNativeStringUtil.stringRepr(((AssignmentExpression)e).getRight());
        _builder.append(_stringRepr_1);
        _builder.append(")");
        _switchResult_1 = _builder.toString();
      }
      if (!_matched_1) {
        if (e instanceof PlusExpression) {
          _matched_1=true;
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("(");
          String _stringRepr = FunPLNativeStringUtil.stringRepr(((PlusExpression)e).getLeft());
          _builder.append(_stringRepr);
          _builder.append(" + ");
          String _stringRepr_1 = FunPLNativeStringUtil.stringRepr(((PlusExpression)e).getRight());
          _builder.append(_stringRepr_1);
          _builder.append(")");
          _switchResult_1 = _builder.toString();
        }
      }
      _switchResult = _switchResult_1;
    }
    if (!_matched) {
      if (e instanceof LiteralExpression) {
        _matched=true;
        String _switchResult_1 = null;
        boolean _matched_1 = false;
        if (e instanceof IntegerLiteralExpression) {
          _matched_1=true;
          StringConcatenation _builder = new StringConcatenation();
          String _string = Integer.valueOf(((IntegerLiteralExpression)e).getValue()).toString();
          _builder.append(_string);
          _switchResult_1 = _builder.toString();
        }
        if (!_matched_1) {
          if (e instanceof BooleanLiteralExpression) {
            _matched_1=true;
            StringConcatenation _builder = new StringConcatenation();
            String _string = Boolean.valueOf(((BooleanLiteralExpression)e).isValue()).toString();
            _builder.append(_string);
            _switchResult_1 = _builder.toString();
          }
        }
        _switchResult = _switchResult_1;
      }
    }
    if (!_matched) {
      if (e instanceof ReferenceExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _name = ((ReferenceExpression)e).getDeclaration().getName();
        _builder.append(_name);
        String ret = _builder.toString();
        boolean _isSetArgumentList = ((ReferenceExpression)e).isSetArgumentList();
        if (_isSetArgumentList) {
          String _ret = ret;
          String _stringRepr = FunPLNativeStringUtil.stringRepr(((ReferenceExpression)e).getArgumentList());
          ret = (_ret + _stringRepr);
        }
        return ret;
      }
    }
    return _switchResult;
  }
  
  protected static String _stringRepr(final TypeDefinition e) {
    String _switchResult = null;
    boolean _matched = false;
    if (e instanceof BasicTypeDefinition) {
      _matched=true;
      String _switchResult_1 = null;
      boolean _matched_1 = false;
      if (e instanceof IntegerTypeDefinition) {
        _matched_1=true;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("int");
        _switchResult_1 = _builder.toString();
      }
      if (!_matched_1) {
        if (e instanceof BooleanTypeDefinition) {
          _matched_1=true;
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("bool");
          _switchResult_1 = _builder.toString();
        }
      }
      if (!_matched_1) {
        if (e instanceof VoidTypeDefinition) {
          _matched_1=true;
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("void");
          _switchResult_1 = _builder.toString();
        }
      }
      _switchResult = _switchResult_1;
    }
    return _switchResult;
  }
  
  public static String stringRepr(final EObject e) {
    if (e instanceof FunctionDeclaration) {
      return _stringRepr((FunctionDeclaration)e);
    } else if (e instanceof ValueDeclaration) {
      return _stringRepr((ValueDeclaration)e);
    } else if (e instanceof Expression) {
      return _stringRepr((Expression)e);
    } else if (e instanceof FunProgram) {
      return _stringRepr((FunProgram)e);
    } else if (e instanceof ReturnStatement) {
      return _stringRepr((ReturnStatement)e);
    } else if (e instanceof TypeDefinition) {
      return _stringRepr((TypeDefinition)e);
    } else if (e instanceof ArgumentList) {
      return _stringRepr((ArgumentList)e);
    } else if (e instanceof Block) {
      return _stringRepr((Block)e);
    } else if (e instanceof ParameterList) {
      return _stringRepr((ParameterList)e);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(e).toString());
    }
  }
}

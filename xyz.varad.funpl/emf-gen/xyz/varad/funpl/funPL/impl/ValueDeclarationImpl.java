/**
 * generated by Xtext 2.15.0
 */
package xyz.varad.funpl.funPL.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import xyz.varad.funpl.funPL.AbstractElement;
import xyz.varad.funpl.funPL.Declaration;
import xyz.varad.funpl.funPL.Expression;
import xyz.varad.funpl.funPL.FunPLPackage;
import xyz.varad.funpl.funPL.NamedElement;
import xyz.varad.funpl.funPL.Type;
import xyz.varad.funpl.funPL.TypeDeclaredElement;
import xyz.varad.funpl.funPL.TypeInferredElement;
import xyz.varad.funpl.funPL.ValueDeclaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link xyz.varad.funpl.funPL.impl.ValueDeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link xyz.varad.funpl.funPL.impl.ValueDeclarationImpl#getDeclaredType <em>Declared Type</em>}</li>
 *   <li>{@link xyz.varad.funpl.funPL.impl.ValueDeclarationImpl#isConstant <em>Constant</em>}</li>
 *   <li>{@link xyz.varad.funpl.funPL.impl.ValueDeclarationImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ValueDeclarationImpl extends MinimalEObjectImpl.Container implements ValueDeclaration {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDeclaredType() <em>Declared Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredType()
	 * @generated
	 * @ordered
	 */
	protected Type declaredType;

	/**
	 * This is true if the Declared Type containment reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean declaredTypeESet;

	/**
	 * The default value of the '{@link #isConstant() <em>Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstant()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONSTANT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConstant() <em>Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstant()
	 * @generated
	 * @ordered
	 */
	protected boolean constant = CONSTANT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression expression;

	/**
	 * This is true if the Expression containment reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean expressionESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FunPLPackage.Literals.VALUE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FunPLPackage.VALUE_DECLARATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getDeclaredType() {
		return declaredType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeclaredType(Type newDeclaredType, NotificationChain msgs) {
		Type oldDeclaredType = declaredType;
		declaredType = newDeclaredType;
		boolean oldDeclaredTypeESet = declaredTypeESet;
		declaredTypeESet = true;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FunPLPackage.VALUE_DECLARATION__DECLARED_TYPE, oldDeclaredType, newDeclaredType, !oldDeclaredTypeESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaredType(Type newDeclaredType) {
		if (newDeclaredType != declaredType) {
			NotificationChain msgs = null;
			if (declaredType != null)
				msgs = ((InternalEObject)declaredType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FunPLPackage.VALUE_DECLARATION__DECLARED_TYPE, null, msgs);
			if (newDeclaredType != null)
				msgs = ((InternalEObject)newDeclaredType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FunPLPackage.VALUE_DECLARATION__DECLARED_TYPE, null, msgs);
			msgs = basicSetDeclaredType(newDeclaredType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldDeclaredTypeESet = declaredTypeESet;
			declaredTypeESet = true;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, FunPLPackage.VALUE_DECLARATION__DECLARED_TYPE, newDeclaredType, newDeclaredType, !oldDeclaredTypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicUnsetDeclaredType(NotificationChain msgs) {
		Type oldDeclaredType = declaredType;
		declaredType = null;
		boolean oldDeclaredTypeESet = declaredTypeESet;
		declaredTypeESet = false;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, FunPLPackage.VALUE_DECLARATION__DECLARED_TYPE, oldDeclaredType, null, oldDeclaredTypeESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDeclaredType() {
		if (declaredType != null) {
			NotificationChain msgs = null;
			msgs = ((InternalEObject)declaredType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FunPLPackage.VALUE_DECLARATION__DECLARED_TYPE, null, msgs);
			msgs = basicUnsetDeclaredType(msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldDeclaredTypeESet = declaredTypeESet;
			declaredTypeESet = false;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.UNSET, FunPLPackage.VALUE_DECLARATION__DECLARED_TYPE, null, null, oldDeclaredTypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDeclaredType() {
		return declaredTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConstant() {
		return constant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstant(boolean newConstant) {
		boolean oldConstant = constant;
		constant = newConstant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FunPLPackage.VALUE_DECLARATION__CONSTANT, oldConstant, constant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
		Expression oldExpression = expression;
		expression = newExpression;
		boolean oldExpressionESet = expressionESet;
		expressionESet = true;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FunPLPackage.VALUE_DECLARATION__EXPRESSION, oldExpression, newExpression, !oldExpressionESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(Expression newExpression) {
		if (newExpression != expression) {
			NotificationChain msgs = null;
			if (expression != null)
				msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FunPLPackage.VALUE_DECLARATION__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FunPLPackage.VALUE_DECLARATION__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldExpressionESet = expressionESet;
			expressionESet = true;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, FunPLPackage.VALUE_DECLARATION__EXPRESSION, newExpression, newExpression, !oldExpressionESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicUnsetExpression(NotificationChain msgs) {
		Expression oldExpression = expression;
		expression = null;
		boolean oldExpressionESet = expressionESet;
		expressionESet = false;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, FunPLPackage.VALUE_DECLARATION__EXPRESSION, oldExpression, null, oldExpressionESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetExpression() {
		if (expression != null) {
			NotificationChain msgs = null;
			msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FunPLPackage.VALUE_DECLARATION__EXPRESSION, null, msgs);
			msgs = basicUnsetExpression(msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldExpressionESet = expressionESet;
			expressionESet = false;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.UNSET, FunPLPackage.VALUE_DECLARATION__EXPRESSION, null, null, oldExpressionESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetExpression() {
		return expressionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getReturnType() {
		return xyz.varad.funpl.typing.FunPLTypeComputer.computeDeepType(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FunPLPackage.VALUE_DECLARATION__DECLARED_TYPE:
				return basicUnsetDeclaredType(msgs);
			case FunPLPackage.VALUE_DECLARATION__EXPRESSION:
				return basicUnsetExpression(msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FunPLPackage.VALUE_DECLARATION__NAME:
				return getName();
			case FunPLPackage.VALUE_DECLARATION__DECLARED_TYPE:
				return getDeclaredType();
			case FunPLPackage.VALUE_DECLARATION__CONSTANT:
				return isConstant();
			case FunPLPackage.VALUE_DECLARATION__EXPRESSION:
				return getExpression();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FunPLPackage.VALUE_DECLARATION__NAME:
				setName((String)newValue);
				return;
			case FunPLPackage.VALUE_DECLARATION__DECLARED_TYPE:
				setDeclaredType((Type)newValue);
				return;
			case FunPLPackage.VALUE_DECLARATION__CONSTANT:
				setConstant((Boolean)newValue);
				return;
			case FunPLPackage.VALUE_DECLARATION__EXPRESSION:
				setExpression((Expression)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case FunPLPackage.VALUE_DECLARATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case FunPLPackage.VALUE_DECLARATION__DECLARED_TYPE:
				unsetDeclaredType();
				return;
			case FunPLPackage.VALUE_DECLARATION__CONSTANT:
				setConstant(CONSTANT_EDEFAULT);
				return;
			case FunPLPackage.VALUE_DECLARATION__EXPRESSION:
				unsetExpression();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case FunPLPackage.VALUE_DECLARATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case FunPLPackage.VALUE_DECLARATION__DECLARED_TYPE:
				return isSetDeclaredType();
			case FunPLPackage.VALUE_DECLARATION__CONSTANT:
				return constant != CONSTANT_EDEFAULT;
			case FunPLPackage.VALUE_DECLARATION__EXPRESSION:
				return isSetExpression();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case FunPLPackage.VALUE_DECLARATION__NAME: return FunPLPackage.NAMED_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == TypeInferredElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == TypeDeclaredElement.class) {
			switch (derivedFeatureID) {
				case FunPLPackage.VALUE_DECLARATION__DECLARED_TYPE: return FunPLPackage.TYPE_DECLARED_ELEMENT__DECLARED_TYPE;
				default: return -1;
			}
		}
		if (baseClass == Declaration.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case FunPLPackage.NAMED_ELEMENT__NAME: return FunPLPackage.VALUE_DECLARATION__NAME;
				default: return -1;
			}
		}
		if (baseClass == TypeInferredElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == TypeDeclaredElement.class) {
			switch (baseFeatureID) {
				case FunPLPackage.TYPE_DECLARED_ELEMENT__DECLARED_TYPE: return FunPLPackage.VALUE_DECLARATION__DECLARED_TYPE;
				default: return -1;
			}
		}
		if (baseClass == Declaration.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", constant: ");
		result.append(constant);
		result.append(')');
		return result.toString();
	}

} //ValueDeclarationImpl




Example code of the intended functionality:

//////////////////////////////////////////////////
var i;				//Variable declaration
var j = 5;			//Variable declaration and definition to int literal
var k = i;			//Variable declaration and definition to other variable
const j = k;		//Constant decl. & def. to variable

function myFunc(p1, p2){		//Function definition
	var ij = 1;					//Variable decl. & def.
	var ijc = ij;				//Variable decl. & def. to variable
	5;							//Terminal expression
	i = 2;						//Assignment (non-terminal) expression
	i = j = 3;					//Chained assignment expression
	i = j = k = 3;				//Longer assignment Chaine
	3 + 5;						//Plus expression
	3 + 5 + 2;					//Longer plus expression
	i = 3 + 5;					//Mixed expression chain
	myFunc2();					//Simple function call (expression) 
	myFunc3(1, 2, 3);			//Function call with terminal arguments	
	myFunc3(1 + 1, 2 + 2 + 2, i = 3);	//Function call with non-terminal arguments
}

function myFunc2(){
	
}

function myFunc3(p1, p2, p3){
	
}

//////////////////////////////////////////////////
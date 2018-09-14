

//Example code of the intended functionality (invalid statements are commented)

var i;			//variable declaration
var j = 5;		//variable decl&def
var k = i;		//vardef to ref
//const j = k;	//constdef to ref


function myFunc(int p1, int p2){//function decl
	var ij = 1;				//var decl
	const ijc = ij;			//const decl
	5;						//terminal expression
	i = 2;					//assignment expression
	i = j = 3;				//recursive assignment expression
	i = j = k = 3;			//an even longer assignment
	3 + 5;					//plus expression
	3 + 5 + 2;				//recursive plus expression
	i = 3 + 5;				//assignment and plus
	myFunc2();				//simple function call
	myFunc3(1, 2, 3);		//function call with terminal arguments
	myFunc3(1+1, 2+2+2, i=3);	//function call with non-terminal arguments
}


function myFunc2(){
	//var p = q;
	//var q = 5;
	
}

function int myFunc3(int p1, int /*p1*/p2, int p3){
	//var p1;
	var o = myFunc;
	
	const m = 1;
	//m = 2;
	
	//5 = 2;
	//myFunc2 = 1;
	
	//oo = 1;

	return 5;
}

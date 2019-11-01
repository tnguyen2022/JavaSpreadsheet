Each Function args field value cannot be empty (that is, each function needs at least 1 argument in order to be evaluated)

LEFT function:
Substring from a string or number, starting from the left-most character
Return value is always a string
Ex. = (LEFT "hello" 4) -> "hell" or = (LEFT 123.45 4) -> "123." or (LEFT "hi" 4) -> "hi"

TODO:
ENUMS
JAVADOCS
README
CYCLIC
MAIN
PARSER


Cycles method check in constructor


--------------------------------------- PACKAGES -----------------------------------------

- CellContentVisitorFunctions 		Visitor classes for spreadsheet functions

- Function				Where the function interface/classes are placed

- Value 				Where the value interface/classes are placed

- sexp 					starter code


-------------------------------------- INTERFACES -----------------------------------------

- Function 				Represents the Functions that can be used in a 
					spreadsheet

- Value					Represents a value that can be inputted in a Cell

- CellContent				Represents the possible contents to be placed into 
					a Cell

- CellContentVisitor			Abstracted function object for processing any Cell
					Content

- Formula				Represents a formula

- GeneralWorkSheet			Represents a basic worksheet

- Sexp					Represents an s-expression

- SexpVisitor				An abstracted function object for processing any Sexp


---------------------------------------- CLASSES ------------------------------------------

- Left					Function object for Left function in a spreadsheet

- LessThan				Function object for Less Than function in a spreadsheet	

- Product				Function object for Product function in a spreadsheet

- Sum					Function object for Sum function in a spreadsheet

- LeftFunction				Function that returns the contents starting from the 
					left from a given number

- LessThanFunction			Represents the less than function

- ProductFunction			Represents the product function
		
- SumFunction				Represents the sum function
	
- BooleanValue				Represents a boolean value

- DoubleValue				Represents a double value
	
- StringValue				Represents a string value

- Blank					Represents a blank class 

- BuildWorksheet			Creates a representation of the worksheet

- Cell					Represents a cell in a spreadsheet

- Coord					A value type representing coordinates in a Worksheet

- Reference				Represents a references in a spreadsheet

- Worksheet				Represents a worksheet spreadsheet

- WorksheetReader			Factory for reading inputs and producing worksheets

- Parser				Starter code (a simple parser for Sexps)

- SBoolean				Starter code (a boolean constant)

- SList					Starter code (represents a list s-expression)

- SNumber				Starter code (represents a numeric constant)

- SString				Starter code (a string constant)

- SSymbol				Starter code (an arbitrary symbol)

- BeyondGood				Main method













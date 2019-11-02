Assumptions: 
> Each Function args field value cannot be empty (that is,
each function needs at least 1 argument in order to be evaluated)
> Cells cannot directly reference themselves (ex. A1 = A1 will throw an Exception)

[Special Added Function Feature]
LEFT function:
Substring from a string or number, starting from the left-most character
Return value is always a string
Ex. = (LEFT "hello" 4) -> "hell" or = (LEFT 123.45 4) -> "123." or (LEFT "hi" 4) -> "hi"

--------------------------------------- PACKAGES -----------------------------------------

- CellContentVisitorFunctions 		Visitor classes for spreadsheet functions

- Function				Where the function interface/classes are placed

- Value 				Where the value interface/classes are placed

- sexp 					starter code


-------------------------------------- INTERFACES -----------------------------------------

- Function 				Represents the Functions that can be used in a 
					spreadsheet, extends CellContent

- Value					Represents a Value representation in a Cell,
                    extends Formula and CellContent

- CellContent				Represents the possible contents to be placed into 
					a Cell

- CellContentVisitor			Abstracted Visitor function object for processing any Cell
					Content

- Formula				Represents a formula, extends CellContent

- GeneralWorkSheet			Represents a basic worksheet

- Sexp					Represents an s-expression

- SexpVisitor				An abstracted visitor function object for processing any Sexp


---------------------------------------- CLASSES ------------------------------------------

- Left					Function object for Left function in a spreadsheet,
                    Implements CellContentVisitor

- LessThan				Function object for Less Than function in a spreadsheet,
                    Implements CellContentVisitor

- Product				Function object for Product function in a spreadsheet,
                    Implements CellContentVisitor

- Sum					Function object for Sum function in a spreadsheet,
                    Implements CellContentVisitor

- LeftFunction				Function that returns the contents starting from the 
					left from a given number, Implements Function

- LessThanFunction			Represents the less than function
                    (Strictly compares two numeric values), Implements Function

- ProductFunction			Represents the product function that finds the total product given
                    a number of arguments, Implements Function
		
- SumFunction				Represents the sum function that finds the total sum given a number of
                    arguments, Implements Function
	
- BooleanValue				Represents a boolean value, Implements Value

- DoubleValue				Represents a double value, Implements Value
	
- StringValue				Represents a string value, Implements Value

- Blank					Represents a blank class, Implements CellContent

- BuildWorksheet			Creates a representation of the worksheet,
                        Implements WorksheetBuilder<BasicWorksheet>

- Cell					Represents a cell in a spreadsheet

- Coord					A value type representing coordinates in a Worksheet

- Reference				Represents a references in a spreadsheet, Implements Formula

- BasicWorksheet		Represents a worksheet spreadsheet, Implements GeneralWorksheet

- WorksheetReader		Factory for reading inputs and producing worksheets

- Parser				Starter code (a simple parser for Sexps),

- SBoolean				Starter code (a boolean constant), implements Sexp

- SList					Starter code (represents a list s-expression), implements Sexp

- SNumber				Starter code (represents a numeric constant), implements Sexp

- SString				Starter code (a string constant), implements Sexp

- SSymbol				Starter code (an arbitrary symbol), implements Sexp

- BeyondGood			Main method that initializes the spreadsheet














# Bughouse FEN Validator

A javascript/java library to validate Chess FEN strings.
[Bughouse](https://en.wikipedia.org/wiki/Bughouse_chess) is the name of a chess game played by 4 players on 2 boards, where pieces captured on one board can be given to players on the other board.  
"bughouse" in the name was meant to indicate that this validator is not all encompassing and comprehensive as it only checks if position is playable as opposed to legal. I.e. it will only check for showstoppers e.g. it will invalidate a position with more than 1 king of a one color, but won't a position with more than 8 pawns of one color etc.
At the same time, this validator does go beyound typical pure regex validation.

# Utility

Written in both js and java, it can be used both on html/js front end and java back end of the same application.


# Installation

### js

place `/resource/static/js/chess.js` in `js` dir of your app. E.g. in Springboot in `/resource/static/js`


### java

Run `mvn clean install` and add the following to your application pom, if you are using maven.

		<dependency>
			<groupId>org.bughouse.fen</groupId>
			<artifactId>bughouse-fen-validator</artifactId>
			<version>1.0</version>
		</dependency>

For gradle, it should be <br/>
`compile 'org.bughouse.fen:bughouse-fen-validator:1.0'` , <br/>
although I haven't tried it myself.

# Usage

### html
add a reference to chess.js in your html file:

	     <script src="js/chess.js"></script>

### js
call validateFen() with your FEN string, and then depending on the value of `valid` property of the returned object, either do error handling (e.g. display the error using `error` property) or continue with happy path screnario.

            const err = validateFen(fen);  
            if (!err.valid) {
            	// error handling e.g.
                // alert(err.error);
                // return;
            } 


### java
add the following in your application code e.g. before sending a FEN to a chess engine:

        ReturnCode returnCode = FenValidator.getInstance().validate(query.getFen());
        if (!returnCode.isValid()) {
        	// Error handling e.g.
		// throw new ResponseStatusException(HttpStatus.CONFLICT, returnCode.getDescription());
        }

        if using in a web service end point in SpringBoot, as in example above, add the following line to your application.properties file, so the error description does actually show up in the response:
        server.error.include-message=always


### command line

	   To check individual FENs in command prompt, replace the prepopulated FEN value in the line below in your pom.xml with yours and execute mvn exec:java

    	<argument>KrkR4/8/8/8/8/8/8/8 w - - 0 1</argument>     


# Credits

The bughouse FEN validator is reusing regex validation logic of [chess.js](https://github.com/jhlywa/chess.js), plus additing extra validation rules on top of it. <br/> The java layer is implemented using [Nashorn JavaScript engine for Java](https://github.com/openjdk/nashorn)


# License

[GNU General Public License V3](https://www.gnu.org/licenses/gpl-3.0.en.html) except for chess.js which has its on license in the comments of the file header


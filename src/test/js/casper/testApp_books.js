var URL = casper.cli.get('home');

casper.test.begin('test application book module', 1, function suite(test) {
	casper.start(URL + "#/books", function() {        
		test.assertTextExists('Books', 'body contains books"');
    });
	
	casper.run(function() {
        test.done();
    });	
});
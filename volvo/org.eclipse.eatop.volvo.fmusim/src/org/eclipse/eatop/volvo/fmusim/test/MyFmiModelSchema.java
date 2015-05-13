package org.eclipse.eatop.volvo.fmusim.test;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

public class MyFmiModelSchema extends SchemaOutputResolver {
	 
    public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
    	 // create new file
        File file = new File("c://fmusim//test.txt");
        
        // create stream result
        StreamResult result = new StreamResult(file);
        
        // set system id
        result.setSystemId(file.toURI().toURL().toString());
        return result;
    }
}

package co.riverrunners.jaws.central.rest;

import co.riverrunners.jaws.central.services.WorkDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Simpple rest call to test the jms producer.
 *
 * In your browser do  http://localhost:12398/jms/somemessage
 * And it will send a somemessage over jms to the rc.
 *
 *
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
@Controller
@RequestMapping("/jms")
public class TestJms {

    @Autowired
    private WorkDispatcher workDispatcher;

    @RequestMapping(value = "/{message}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> testJms(@PathVariable String message){
        workDispatcher.testSend(message);
        return new ResponseEntity<String>("Sent Message", HttpStatus.OK);
    }


}

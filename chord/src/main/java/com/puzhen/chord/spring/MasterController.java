package com.puzhen.chord.spring;

import com.puzhen.chord.consistenthashing.DistributedHashTable;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MasterController {

    static final Logger logger = Logger.getLogger(MasterController.class);
    private DistributedHashTable distributedHashTable = DistributedHashTable.getInstance();

    @RequestMapping(value = "/put", method=RequestMethod.PUT,
        params = {"key", "value"})
    @ResponseBody
    public String doPut(@RequestParam (value = "key") String key,
                        @RequestParam (value = "value") String value) {
        distributedHashTable.put(key, value);
        return "Done";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, params = {"key"})
    @ResponseBody
    public String doGet(@RequestParam (value = "key") String key) {
        return distributedHashTable.get(key);
    }

    @RequestMapping(value = "/requestForLeave", method = RequestMethod.POST,
        params = {"portNumber"})
    @ResponseBody
    public String answerLeaveRequest(@RequestParam (value = "portNumber") String portNumber) {
        logger.info("Meeting port " + portNumber + ", granting its leave..");
        String successingServerPort = distributedHashTable.getSuccessingServer(portNumber);
        distributedHashTable.dismissSlave(portNumber);
        return "OK " + successingServerPort;
        //return "OK";
    }
}

package com.puzhen.chord.spring;

import com.puzhen.chord.consistenthashing.DistributedHashTable;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MasterController {

    static final Logger logger = Logger.getLogger(MasterController.class);
    private DistributedHashTable distributedHashTable = DistributedHashTable.getInstance();
    private List<String> slaves = new ArrayList<>();

    @RequestMapping(value = "/put", method=RequestMethod.PUT,
        params = {"key", "value"})
    @ResponseBody
    public String doPut(@RequestParam (value = "key") String key,
                        @RequestParam (value = "value") String value) {
        if (slaves.size() == 0) {
            return "Master can't do this now because there is no slaves.";
        }
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

    @RequestMapping(value = "/requestForEnter", method = RequestMethod.POST,
        params = {"portNumber"})
    @ResponseBody
    public String answerEnterRquest(@RequestParam(value = "portNumber") String portNumber) {
        slaves.add(portNumber);
        distributedHashTable.addSlave(portNumber);
        if (slaves.size() == 1) {
            logger.info("It's the first slave, no need to move keys");
        } else {
            String succeedingSlave = distributedHashTable.getSuccessingServer(portNumber);
            try {
                HttpURLConnection conn = (HttpURLConnection) (new URL("http://localhost:" + succeedingSlave +
                        "/newComerNotification?newComerPort=" + portNumber)).openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                logger.info("Response from slave: " + rd.readLine());
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("I should notify slave " + succeedingSlave + ", but I can't now.");
                return "Error";
            }

        }
        return "OK";
    }
}

package com.puzhen.chord.spring;

import com.puzhen.chord.consistenthashing.DistributedHashTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MasterController {

    private DistributedHashTable distributedHashTable = new DistributedHashTable();

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
}

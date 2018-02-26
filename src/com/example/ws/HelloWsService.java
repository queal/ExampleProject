package com.example.ws;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface HelloWsService {

	String sayHi(@WebParam(name = "text") String text, @WebParam(name = "t1") String t1);
}

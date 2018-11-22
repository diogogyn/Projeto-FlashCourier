package org.dos.controller;

import com.google.gson.Gson;
import org.dos.model.Rastreio;
import org.dos.repository.RastreioRepository;
import org.dos.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

public class RastreioController {

    //@Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    RastreioRepository rastreioRepository = null;


    @RequestMapping(value = "/buscarastreio/{idRastreio}", method = RequestMethod.GET)
    public @ResponseBody
    Rastreio buscarRastreio(@PathVariable("idRastreio") int idRastreio) {
        return rastreioRepository.buscarDetalhesRastreio(idRastreio);
    }

    @RequestMapping(value = "/buscarastreio", method = RequestMethod.POST)
    public @ResponseBody
    Rastreio buscaRastreio(@RequestBody Rastreio r) {
        System.out.println("EU RECEBI: "+r);
        return rastreioRepository.buscarDetalhesRastreio(r.getId());
    }

    @RequestMapping(value = "/listCertificates", method = RequestMethod.GET)
    public @ResponseBody
    String listarRastreios() {
        Gson gson = new Gson();
        return gson.toJson(rastreioRepository.listaRastreios());
    }

}


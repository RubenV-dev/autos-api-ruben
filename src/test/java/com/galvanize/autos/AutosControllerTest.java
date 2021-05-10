package com.galvanize.autos;

public class AutosControllerTest {
    // GET: /api/autos returns list of all autos in database (200)
    // GET: /api/autos no autos in database returns 204 no content
    // GET: /api/autos?color=blue returns all blue cars
    // GET: /api/autos?make=toyota returns all toyotas
    // GET: /api/autos?color=blue&make=toyota returns all blue toyotas

    // POST: /api/autos adds automobile & returns it when given correct params (200)
    // POST: /api/autos bad params returns 400 (bad request)

    // GET: /api/autos/{vin} returns auto by VIN (id) number
    // GET: /api/autos/{vin} returns 204 (no content) when no vehicle with that vin is found

    // PATCH: /api/autos/{vin} returns patched automobile with supplied vin
    // PATCH: /api/autos/{vin} returns 204 (no content) when no vehicle with that vin is found
    // PATCH: /api/autos/{vin} returns 400 (bad request) when no payload, no changes or already done

    // DELETE: /api/autos/{vin} deletes auto with specified VIN (202)
    // DELETE: /api/autos/{vin} 204 (no content) when there is no vehicle with that VIN

}

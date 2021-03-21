INSERT INTO pvmodules(manufacturer, model, type, power, currentstc, maxcurrentstc, voltagestc, voltagempp,
temperaturelost, efficency, price)
VALUES ('BYD', 'AX250', 1, 250, 0.5, 2.0, 3.5, 7.0, -0.28, 0.20, 0.25);


INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Baywa', 'Aero', 0, 3, 20);


INSERT INTO inverter(manufacturer, model, type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob,
dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values ('Fronius', 'SunPower', 1, 800, 800, 2, 9.12, 10.14, 200, 800, 1000, 10000);

INSERT INTO instalation(pvmodule_id,numberofpvmodule,inverter_id,construction_id,power) values(1,20,1,1,200);


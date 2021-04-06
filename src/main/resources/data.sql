INSERT INTO pvmodules(manufacturer, model, type, power, currentstc, maxcurrentstc, voltagestc, voltagempp,
temperaturelost, efficency, price)
VALUES ('BYD', 'AX250', 1, 250, 0.5, 2.0, 3.5, 7.0, 0.28, 0.20, 0.25);


INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent A', 'Balastowy', 'DACH_PLASKI', 'PAPA', 150);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent B', 'Balastowy', 'DACH_PLASKI', 'PAPA', 170);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent A', 'B1', 'DACH_SKOSNY', 'BLACHODACHOWKA', 90);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent B', 'BX1', 'DACH_SKOSNY', 'BLACHODACHOWKA', 80);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent A', 'B2', 'DACH_SKOSNY', 'BLACHOTRAPEZ', 30);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent B', 'BX2', 'DACH_SKOSNY', 'BLACHOTRAPEZ', 50);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent A', 'P1', 'DACH_SKOSNY', 'PLYTA_WARSTWOWA', 110);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent B', 'PX1', 'DACH_SKOSNY', 'PLYTA_WARSTWOWA', 95);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent A', 'G1', 'DACH_SKOSNY', 'GONT', 80);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent B', 'GX1', 'DACH_SKOSNY', 'GONT', 100);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent A', 'DC1', 'DACH_SKOSNY', 'DACHÓWKA_CERAMICZNA', 150);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent B', 'DCX1', 'DACH_SKOSNY', 'DACHÓWKA_CERAMICZNA', 140);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent A', 'DK1', 'DACH_SKOSNY', 'DACHÓWKA_KARPIÓWKA', 70);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent B', 'DKX1', 'DACH_SKOSNY', 'DACHÓWKA_KARPIÓWKA', 60);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent A', 'G1', 'GRUNT', 'GRUNT', 110);
INSERT INTO construction(manufacturer, model, rooftype, roofmaterial, price)
values ('Producent B', 'GX1', 'GRUNT', 'GRUNT', 125);


INSERT INTO inverter(manufacturer, model, type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob,
dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values ('Fronius', 'SunPower', 1, 80, 80, 2, 9.12, 10.14, 200, 800, 1000, 10000);

INSERT INTO instalation(pvmoduleid, numberofpvmodule, inverterid, numberofinverters, constructionid, power, price)
values (1, 20, 1, 1, 1, 20*250, 30000);

INSERT INTO owner(firstname, secondname,phonenumber,additionalphonenumber,mailadress)
values ('Jan','Kowalski',123456789,null,'ABCDEFGHJ@XYZ.PL');

INSERT INTO address(district, city,postalcode,street,housenumber,ownerid)
values (5,'Kraków','Wawel','41250',4,1);




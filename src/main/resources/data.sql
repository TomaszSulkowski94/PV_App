INSERT INTO pvmodules(manufacturer, model, type, power, currentstc, maxcurrentstc, voltagestc, voltagempp,
temperaturelost, efficency, price)
VALUES ('Producent X', 'AM315', 1, 315, 9.03, 8.53, 45.33, 36.93, 0.299, 0.25, 0.25);
INSERT INTO pvmodules(manufacturer, model, type, power, currentstc, maxcurrentstc, voltagestc, voltagempp,
temperaturelost, efficency, price)
VALUES ('Producent X', 'AM340', 1, 340, 10.3, 9.8, 41.88, 34.69, 0.285, 0.203, 0.21);
INSERT INTO pvmodules(manufacturer, model, type, power, currentstc, maxcurrentstc, voltagestc, voltagempp,
temperaturelost, efficency, price)
VALUES ('Producent W', 'WP250', 0, 250, 9.22, 8.68, 37.85, 32.26, 0.285, 0.1691, 0.28);
INSERT INTO pvmodules(manufacturer, model, type, power, currentstc, maxcurrentstc, voltagestc, voltagempp,
temperaturelost, efficency, price)
VALUES ('Producent W', 'WM315', 1, 315, 9.87, 9.41, 40.94, 33.5, 0.254, 0.1903, 0.27);
INSERT INTO pvmodules(manufacturer, model, type, power, currentstc, maxcurrentstc, voltagestc, voltagempp,
temperaturelost, efficency, price)
VALUES ('Producent W', 'WM340', 1, 340, 9.45, 8.92, 45.73, 38.15, 0.254, 0.2022, 0.251);
INSERT INTO pvmodules(manufacturer, model, type, power, currentstc, maxcurrentstc, voltagestc, voltagempp,
temperaturelost, efficency, price)
VALUES ('Producent C', 'CM315', 1, 315, 9.52, 10.06, 39.9, 33.1, 0.29, 0.1896, 0.3);
INSERT INTO pvmodules(manufacturer, model, type, power, currentstc, maxcurrentstc, voltagestc, voltagempp,
temperaturelost, efficency, price)
VALUES ('Producent C', 'CM360', 1, 360, 11.45, 10.88, 40.2, 33.3, 0.29, 0.1946, 0.24);
INSERT INTO pvmodules(manufacturer, model, type, power, currentstc, maxcurrentstc, voltagestc, voltagempp,
temperaturelost, efficency, price)
VALUES ('Producent C', 'CB330', 2, 330, 10.46, 9.82, 41.36, 34.63, 0.289, 0.202, 0.39);
INSERT INTO pvmodules(manufacturer, model, type, power, currentstc, maxcurrentstc, voltagestc, voltagempp,
temperaturelost, efficency, price)
VALUES ('Producent C', 'CG320', 3, 320, 10.01, 9.24, 40.2, 33.6, 0.27, 0.186, 0.4);
INSERT INTO pvmodules(manufacturer, model, type, power, currentstc, maxcurrentstc, voltagestc, voltagempp,
temperaturelost, efficency, price)
VALUES ('Producent R', 'RM310', 1, 310, 10.01, 9.24, 40.1, 32.6, 0.27, 0.186, 0.27);


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

INSERT INTO inverter(manufacturer, model, type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'STL-X', 0, 1500, 1000, 1, 11.00, 14.00, 160, 840, 1000, 4200);
INSERT INTO inverter(manufacturer, model, type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'STL-X', 0, 2000, 1500, 1, 11.00, 14.00, 160, 840, 1000, 4300);
INSERT INTO inverter(manufacturer, model, type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'STL-X', 0, 3000, 2000, 1, 11.00, 14.00, 160, 840, 1000, 4500);
INSERT INTO inverter(manufacturer, model, type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'STL-X', 1, 4000, 3000, 2, 11.00, 14.00, 160, 840, 1000, 4000);
INSERT INTO inverter(manufacturer, model, type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'STL-X', 1, 5000, 4000, 2, 11.00, 14.00, 190, 840, 1000, 4100);
INSERT INTO inverter(manufacturer,model, type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob,dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'STL-X', 1, 6000, 5000, 2, 11.00, 14.00, 190, 840, 1000, 4300);
INSERT INTO inverter(manufacturer,model, type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob,dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'STL-X', 1, 7200, 6000, 2, 11.00, 14.00, 290, 840, 1000, 4500);
INSERT INTO inverter(manufacturer,model, type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob,dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'STL-X', 1, 8400, 7000, 2, 11.00, 14.00, 290, 840, 1000, 4600);
INSERT INTO inverter(manufacturer,model, type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob,dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'STL-X', 1, 9100, 8000, 2, 11.00, 14.00, 290, 840, 1000, 4700);
INSERT INTO inverter(manufacturer,model, type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob,dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'STL-X', 1, 10200, 9000, 2, 11.00, 14.00, 290, 840, 1000, 4850);
INSERT INTO inverter(manufacturer,model, type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob,dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'STL-X', 1, 11200, 10000, 2, 11.00, 14.00, 290, 840, 1000, 4950);
INSERT INTO inverter(manufacturer,model, type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob,dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'STL-X', 1, 12200, 11000, 2, 11.00, 14.00, 300, 840, 1000, 5200);
INSERT INTO inverter(manufacturer, model , type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'STL-X', 1, 15000, 12000, 2, 11.00, 14.00, 300, 800, 1000, 5500);
INSERT INTO inverter(manufacturer, model , type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'SL', 1, 18000, 15000, 2, 21.00, 27.00, 370, 850, 1000, 6000);
INSERT INTO inverter(manufacturer, model , type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'SL', 1, 20400, 17000, 2, 24.00, 30.00, 420, 850, 1000, 6500);
INSERT INTO inverter(manufacturer, model , type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'SL', 1, 24000, 20000, 2, 24.00, 30.00, 430, 850, 1000, 8000);
INSERT INTO inverter(manufacturer, model , type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'SL', 1, 28800, 24000, 2, 24.00, 30.00, 430, 850, 1000, 9000);
INSERT INTO inverter(manufacturer, model , type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'SL', 1, 30000, 27000, 2, 24.00, 30.00, 430, 850, 1000, 9300);
INSERT INTO inverter(manufacturer, model , type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'SL', 1, 33000, 30000, 2, 12.00, 40.00, 250, 960, 1000, 9500);
INSERT INTO inverter(manufacturer, model , type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'SL', 1, 36000, 33000, 2, 12.00, 44.00, 250, 960, 1000, 12000);
INSERT INTO inverter(manufacturer, model , type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'SL', 1, 39000, 36000, 2, 12.00, 47.00, 250, 960, 1000, 15000);
INSERT INTO inverter(manufacturer, model , type, dcpower, acpower, mppt, maxcurrentzwarcia, maxcurrentrob, dolnyzakresnapiecia, gornyzakresnapiecia, maksymalnenapiecie, price)
values('Producent S', 'SL', 1, 48000, 40000, 2, 12.00, 44.00, 250, 960, 1000, 18000);

INSERT INTO questionform(bill, rooftype,roofslope,roofposition,roofmaterial)
values(80,'DACH_PLASKI', 'Dach_płaski','Południowy_Wschód_20','PAPA');

INSERT INTO production(january,february,march,april,may,june,july,august,september,october,november,december,summary)
values (189,252,441,567,756,819,756,819,693,567,315,189,6363);

INSERT INTO instalation(pvmoduleid, numberofpvmodule, inverterid, numberofinverters, constructionid, power, price, questionformid, productionid)
values(1, 20, 5, 1, 1, 20 * 250, 20000,1, 1);



INSERT INTO owner(firstname, secondname, phonenumber, additionalphonenumber, mailadress)
values('Jan', 'Kowalski', 123456789, null, 'ABCDEFGHJ@XYZ.PL');

INSERT INTO address(district, city, postalcode, street, housenumber, ownerid)
values(5, 'Kraków', 'Wawel', '41250', 4, 1);




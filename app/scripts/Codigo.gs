const API_KEY = PropertiesService.getScriptProperties().getProperty("API_KEY");

function doGet(e) {

  const apiKey = (e.parameter.apiKey || "").trim();
  if (apiKey !== API_KEY) throw new Error("Unauthorized: API key incorrecta.");

  const type = (e.parameter.type || "").trim();

  const sheet = SpreadsheetApp.getActiveSpreadsheet().getSheetByName("SmartphoneData");
  const data = sheet.getDataRange().getValues();

  const headers = data[0];
  const jsonData = [];

  for (let i = 1; i < data.length; i++) {

    let row = {};

    for (let j = 0; j < headers.length; j++) {
      row[headers[j]] = data[i][j];
    }

    jsonData.push(row);
  }

  // Primer endpoint, totes les dades
  if (type === "data") {

    const response = {
      status: "ok",
      totalRecords: jsonData.length,
      data: jsonData
    };

    return ContentService
      .createTextOutput(JSON.stringify(response))
      .setMimeType(ContentService.MimeType.JSON);
  }

  // Segon endpoint, filtrar per ocupació
  if (type === "occupation") {

    const value = (e.parameter.value || "").trim();

    const filtered = jsonData.filter(r => r.occupation === value);

    const response = {
      status: "ok",
      occupation: value,
      total: filtered.length,
      data: filtered
    };

    return ContentService
      .createTextOutput(JSON.stringify(response))
      .setMimeType(ContentService.MimeType.JSON);
  }

  // Tercer endpoint, stats
  if (type === "stats") {

    let totalPhone = 0;
    let totalSleep = 0;

    jsonData.forEach(r => {
      totalPhone += Number(r.dailyPhoneHours);
      totalSleep += Number(r.sleepHours);
    });

    const response = {
      status: "ok",
      avgPhoneHours: totalPhone / jsonData.length,
      avgSleepHours: totalSleep / jsonData.length
    };

    return ContentService
      .createTextOutput(JSON.stringify(response))
      .setMimeType(ContentService.MimeType.JSON);
  }

  return ContentService
    .createTextOutput(JSON.stringify({
      status: "error",
      message: "Endpoint no válido"
    }))
    .setMimeType(ContentService.MimeType.JSON);
}

function doPost(e) {

  const body = JSON.parse(e.postData.contents || "{}");

  const apiKey = (body.apiKey || "").trim();
  if (apiKey !== API_KEY) throw new Error("Unauthorized");

  const type = (body.type || "").trim();

  if (type === "insert") {

    const sheet = SpreadsheetApp.getActiveSpreadsheet().getSheetByName("SmartphoneData");

    sheet.appendRow([
      body.age,
      body.gender,
      body.occupation,
      body.dailyPhoneHours,
      body.sleepHours
    ]);

    const response = {
      status: "ok",
      message: "Registro insertado correctamente"
    };

    return ContentService
      .createTextOutput(JSON.stringify(response))
      .setMimeType(ContentService.MimeType.JSON);
  }

  return ContentService
    .createTextOutput(JSON.stringify({
      status: "error",
      message: "POST endpoint no válido"
    }))
    .setMimeType(ContentService.MimeType.JSON);
}

function testGet() {
  const mockData = {
    parameter: {
      apiKey: API_KEY,
      type: "data"
    }
  }
  Logger.log(doGet(mockData).getContent())
};



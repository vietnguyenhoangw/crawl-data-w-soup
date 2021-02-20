const http = require("http");
const sqlite3 = require("sqlite3");
const Promise = require("bluebird");
const striptags = require("striptags");

const hostname = "127.0.0.1";
const port = 3003;

const listDataFromDb = [];
let newData;

let db = new sqlite3.Database(
  "./db/english-vocab-html-raw.db",
  sqlite3.OPEN_READWRITE,
  (err) => {
    if (err) {
      console.error(err.message);
    }
    console.log("Connected to the chinook database.");
  }
);

db.serialize(() => {
  db.each(`SELECT word as w, html_raw as raw FROM vi_word_copy`, (err, row) => {
    if (err) {
      console.error(err.message);
    }
    let data = {
      word: row.w,
      html_raw: row.raw,
    };
    listDataFromDb.push(data);
    handleData();
  });
});

const handleData = () => {
  newData = listDataFromDb.map((item, index) => {
    if (index > 100) {
      return cleanData(item);
    }
  });
};

// clearn data make them become an object
const cleanData = (item) => {
  let data = {
    word: item.word,
  };

  const cleanString = striptags(`${item.html_raw}`);
  const formatString = cleanString !== "null" ? cleanString : "";

  var lines = formatString.split("\n");
  // console.log("lines.length >>>", lines.length);
  if (lines.length === 1) {
    // console.log(">>> ");
    return "";
  }
  if (lines.length > 1) {
    for (i = 1; i < lines.length; i++) {
      if (i < lines.length - 1) {
        data = {
          ...data,
          ex: {
            ex1: lines[1],
            ex2: lines[2],
            ex3: lines[3],
            ex4: lines[4],
            ex5: lines[5],
          },
        };
      }
    }
    return data;
  }
};

const server = http.createServer((req, res) => {
  console.log("SIZE: ", newData.length);
  res.statusCode = 200;
  res.setHeader("Content-Type", "text/plain");
  res.end("hello world".toString());
});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});

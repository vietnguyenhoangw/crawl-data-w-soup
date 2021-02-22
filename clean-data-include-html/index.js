const http = require("http");
const sqlite3 = require("sqlite3");
const Promise = require("bluebird");
const striptags = require("striptags");

const hostname = "127.0.0.1";
const port = 3003;

const newData = [];
const queryList = [];

let db = new sqlite3.Database(
  "./db/english-vocab-html-clean.db",
  sqlite3.OPEN_READWRITE,
  (err) => {
    if (err) {
      console.error(err.message);
    }
    console.log("Connected to the chinook database.");
  }
);

db.all("SELECT word, html_raw FROM vi_word_copy", {},
  (error, rows) => {
    if (error) {
      console.error(error.message);
    }

    db.serialize(function () {
      rows.forEach((row, index) => {
        let item = {
          word: row.word,
          html_raw: row.html_raw,
        };
        const itemAfterClean = cleanData(item)
        // console.log(`item: ${JSON.stringify(itemAfterClean)} \n\n\n`);
        const itemQuery = `UPDATE vi_word_copy SET example = '${JSON.stringify(itemAfterClean.example)}' WHERE word LIKE "${itemAfterClean.word}"`
        // console.log(">>>>>>: ", itemQuery)

        db.run(itemQuery, function (err) {
          if (err) {
            console.log(">>>:     ", itemAfterClean.example);
            console.log(err);
            console.log(">>>:     ", itemQuery);
          } else {
            console.log("Success !!!");
          }
        })
      })

    })
  });

// clearn data make them become an object
const cleanData = (item) => {
  let data = {
    word: item.word,
  };

  const cleanString = striptags(`${item.html_raw}`);
  const formatString = cleanString !== "null" ? cleanString : "";

  // format raw data to multiple string like
  /**
   * 
   * line 1
   * line 2
   * line 3
   */

  var lines = formatString.split("\n");

  // first line in lines is always empty
  if (lines.length === 1) {
    data = {
      ...data,
      example: ""
    };
    return data;
  }

  // if lines is not empty
  // create new object
  // with new key is "ex[i]"
  if (lines.length > 1) {
    const ex = {}
    for (i = 1; i < lines.length; i++) {
      if (i < lines.length - 1) {
        ex[i] = lines[i].replace(/\'/g, "''");
        data = {
          ...data,
          example: ex
        };
      }
    }

    return data;
  }
};

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader("Content-Type", "text/plain");
  res.end(newData.length.toString());
});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});

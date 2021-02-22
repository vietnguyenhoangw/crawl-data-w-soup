const http = require("http");
const sqlite3 = require("sqlite3");
const striptags = require("striptags");
var _ = require("lodash");

const hostname = "127.0.0.1";
const port = 3003;

// connect with db
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

// select word and raw_html correctsponding
db.all("SELECT word, html_raw FROM vi_word_copy", {}, (error, rows) => {
  if (error) {
    console.error(error.message);
  }

  // serialize will execute the sql command from first line to ...n line
  db.serialize(function () {
    // get the word corresponding with raw_html add to clean data method
    rows.forEach((row) => {
      let item = {
        word: row.word,
        html_raw: row.html_raw,
      };
      // clean data method
      // if raw is empty => return empty
      const itemAfterClean = cleanData(item);

      // check and execute the sql command for
      // add the new clean data to example column
      if (!_.isEmpty(itemAfterClean.example)) {
        const itemQuery = `UPDATE vi_word_copy SET example = '${JSON.stringify(
          itemAfterClean.example
        )}' WHERE word LIKE '${itemAfterClean.word.replace(/\'/g, "''")}'`;
        db.run(itemQuery, function (err) {
          if (err) {
            console.log(err);
          } else {
            console.log("Success !!!");
          }
        });
      } else {
        const itemQuery = `UPDATE vi_word_copy SET example = NULL WHERE word LIKE '${itemAfterClean.word.replace(
          /\'/g,
          "''"
        )}'`;
        db.run(itemQuery, function (err) {
          if (err) {
            console.log(err);
          } else {
            console.log("NULL Success !!!");
          }
        });
      }
    });
  });
});

// clearn data make them become an object
const cleanData = (item) => {
  let data = {
    word: item.word,
  };

  if (_.isEmpty(item.html_raw)) {
    return data;
  }

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
    return data;
  }

  // if lines is not empty
  // create new object
  // with new key is "ex[i]"
  if (lines.length > 1) {
    const ex = {};
    for (i = 1; i < lines.length; i++) {
      if (i < lines.length - 1) {
        ex[i] = lines[i].replace(/\'/g, "''");
        data = {
          ...data,
          word: item.word,
          example: ex,
        };
      }
    }
    return data;
  }
};

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader("Content-Type", "text/plain");
  res.end("hello_word");
});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});

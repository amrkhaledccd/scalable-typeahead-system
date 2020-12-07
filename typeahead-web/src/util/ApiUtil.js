const API_BASE_URL = "http://localhost:8081";

const request = options => {
  const headers = new Headers();

  if (options.setContentType !== false) {
    headers.append("Content-Type", "application/json");
  }

  const defaults = { headers: headers };
  options = Object.assign({}, defaults, options);

  return fetch(options.url, options).then(response =>
    response.json().then(json => {
      if (!response.ok) {
        return Promise.reject(json);
      }
      return json;
    })
  );
};

export function addPhrase(phrase) {
  return request({
    url: API_BASE_URL + "/trie/phrase",
    method: "POST",
    body: phrase
  });
}

export function autocomplete(prefix, filter) {
  var url = API_BASE_URL + "/trie/phrase/" + prefix;

  console.log(url);

  return request({
    url: url,
    method: "GET"
  });
}

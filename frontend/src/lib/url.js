const mode = 'production'
// const mode = 'local'

let urlValString

if (mode === 'production') {
  urlValString = 'http://13.209.101.187:8090'
} else if (mode === 'local') {
  urlValString = 'http://localhost:8090'
}

export const urlVal = urlValString

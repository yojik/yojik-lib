load("text-hatena0-2.js")
load("config.js");
function replaceAndEval(str) {
  return eval(str);
}
function save(text) {
 try {
  var fw = new java.io.FileWriter("result.html")
  fw.write(result);
  fw.flush()
 } catch (e) {
  print(e); 
 } finally {
  if(fw!=null) fw.close()
 }
}
function wiki2html(str) {
  var interview = new Hatena({ sectionanchor : config.sectionanchor});
  interview.parse(readFile(str));
  return  interview.html();
}
template = readFile(config.template);
var result = template.replace(/{.*}/g,replaceAndEval);
save(result);

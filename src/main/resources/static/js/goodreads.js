/*
let bookcollection = [{
    "Title": "Linux in easy steps",
    "Author": "Mike McGrath",
    "Category": "Operating systems",
    "Price": 16.99,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781840789379&issn=/MC.GIF"
},{
    "Title": "Java in easy steps",
    "Author": "Mike McGrath",
    "Category": "Computer programming",
    "Price": 15.99,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781840787535&issn=/MC.GIF"
},{
    "Title": "JavaScript in easy steps",
    "Author": "Mike McGrath",
    "Category": "Computer programming",
    "Price": 15.99,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=1140154076&isbn=9781840788778&issn=/MC.GIF"
},{
    "Title": "How to Cook Everything: Vegetarian",
    "Author": "Mark Bittman",
    "Category": "Cooking, Food",
    "Price": 35,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9780764524837&issn=/MC.GIF"
},{
    "Title": "How to Cook Everything Fast: a better way to cook great food",
    "Author": "Mark Bittman",
    "Category": "Cooking, Food",
    "Price": 37,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=880705562&isbn=9780470936306&issn=/MC.GIF"
},{
    "Title": "American wholefoods cuisine : 1300 meatless wholesome recipes from short order to gourmet",
    "Author": "Nikki Goldbeck",
    "Category": "Cooking, Food",
    "Price": 21.95,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=978188610111&oclc=&isbn=9781886101111&issn=/MC.GIF"
},{
    "Title": "Mathematical Puzzles and Curiosities",
    "Author": "Barry R. Clarke",
    "Category": "Mathematics",
    "Price": 9.95,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9780486315720&issn=/LC.JPG"
},{
    "Title": "What in the world? : fun-tastic photo puzzles for curious minds",
    "Author": "Julie Vosburgh Agnone",
    "Category": "Optical illusions, puzzles",
    "Price": 16.99,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781426315176&issn=/MC.GIF"
},{
    "Title": "Amazing rivers : 100+ waterways that will boggle your mind",
    "Author": "Julie Vosburgh Agnone",
    "Category": "Rivers",
    "Price": 24,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781912920266&issn=/MC.GIF"
},{
    "Title": "Origin of everyday things",
    "Author": "Johnny Acton",
    "Category": "Encyclopedias",
    "Price": 10.95,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781402743023&issn=/MC.GIF"
},{
    "Title": "International encyclopedia of women and sports",
    "Author": "Karen Christensen",
    "Category": "Encyclopedias",
    "Price": 495,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9780028649542&issn=/MC.GIF"
},{
    "Title": "Thoughtful gardening",
    "Author": "Robin Lane Fox",
    "Category": "Gardening",
    "Price": 17.99,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9780465021963&issn=/MC.GIF"
},{
    "Title": "Guide to Minnesota vegetable gardening",
    "Author": "James Alfred Fizzell",
    "Category": "Gardening",
    "Price": 13.45,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=166368305&isbn=9781591864035&issn=/LC.JPG"
},{
    "Title": "Fairy gardening : creating your own magical miniature garden",
    "Author": "Julie Bawden-Davis",
    "Category": "Gardening",
    "Price": 16.95,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781616088330&issn=/MC.GIF"
},{
    "Title": "Observatories in space",
    "Author": "Darlene R. Stille",
    "Category": "Astronomy, Outer space",
    "Price": 4.75,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9780716624981&issn=/MC.GIF"
},{
    "Title": "Astronomy lab for kids : 52 family-friendly activities",
    "Author": "Michelle Nichols",
    "Category": "Astronomy",
    "Price": 24.99,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781631591341&issn=/MC.GIF"
},{
    "Title": "Dark skies : a practical guide to astrotourism",
    "Author": "Valerie Stimac",
    "Category": "Astronomy",
    "Price": 22.99,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781788686198&issn=/MC.GIF"
},{
    "Title": "Space exploration : a history in 100 objects",
    "Author": "Sten F. Odenwald",
    "Category": "Astronomy, Outer space",
    "Price": 25,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781615196142&issn=/MC.GIF"
},{
    "Title": "Thinking better : the art of the shortcut in math and life",
    "Author": "Marcus Du Sautoy",
    "Category": "Mathematics",
    "Price": 26.99,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781541600362&issn=/MC.GIF"
},{
    "Title": "How mathematics happened : the first 50,000 years",
    "Author": "Peter Strom Rudman",
    "Category": "Mathematics",
    "Price": 26,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781591024774&issn=/LC.JPG"
},{
    "Title": "The Storyteller: Tales of Life and Music",
    "Author": "Dave Grohl",
    "Category": "Autobiography, Music",
    "Price": 29.99,
    "ImageURL": "https://booksite-app.appspot.com/coverart/7295/9780063076099.jpg"
},{
    "Title": "These Precious Days: Essays",
    "Author": "Ann Patchett",
    "Category": "Essays",
    "Price": 18,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=1262750080&isbn=9780063136847&issn=/MC.GIF"
},{
    "Title": "How to be perfect : the correct answer to every moral question",
    "Author": "Michael Schur",
    "Category": "Humor",
    "Price": 28.99,
    "ImageURL": "https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=1281791543&isbn=9781982159313&issn=/MC.GIF"
},{
    "Title": "Taste: My Life Through Food",
    "Author": "Stanley Tucci",
    "Category": "Autobiography, Food",
    "Price": 28,
    "ImageURL": "https://img1.od-cdn.com/ImageType-200/0439-1/%7BA37418F1-6792-467B-9C57-A9E2A94C9A18%7DImg200.jpg"
}
];

booksElement = document.getElementById("books");

for (let i = 0; i < bookcollection.length; i++) {
    let newbook = bookcollection[i];
    let bookdata =  "<tr><td class=\"ratio1\"><img src=\"" + newbook.ImageURL + "\"/></td>" +
                    "<td class=\"ratio2\">" + newbook.Title + "</td>" +
                    "<td class=\"ratio4\">" + newbook.Author + "</td></tr>";

    booksElement.innerHTML += bookdata;
}
*/
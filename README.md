1)send a post request to api in this format:https://vast-eyrie-18750.herokuapp.com/add
{
  "centername": "nn",
  "centercode": "AA2fdl043012",
  "student_capacity": "120",
  "courses_offered":[
  	{
  		"course_name": "aa"},
  		{
  		"course_name": "2aa"
  	}],
  "address": 
    {
      "detailed_address": "asd",
      "city": "asas",
      "state": "aa",
      "pincode": "101161"
    },
    "contact_phone": 1234567890,
    "course_offered":["bsc","bca"]
  
}
validation annotation are included so that user enters correct data.Center code should be unique. request and response is in json.

2)Send a get request to :https://vast-eyrie-18750.herokuapp.com/getcenters api to fetch all centers.

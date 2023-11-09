print('Start #################################################################');
db = db.getSiblingDB('doctornotes');

db.createUser(
    {
      user: "",
      pwd: "",
      roles: [
        {
          role: "readWrite",
          db: "doctornotes"
        }
      ]
    }
  );


  db.createCollection('doctor_notes');
  db.doctor_notes.insertOne(
    {
        noteId:"65316429f4e3ac9f3e46d8ae",
        patientId:1,
        noteContent:"Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé"
       
    }
  );
  print('END #################################################################');
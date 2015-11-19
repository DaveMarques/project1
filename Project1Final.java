//////// Exercise x2:  modularize exercise x1, and add ghost to chase puck.
//////// Whoever Whoever  (CST 112; today's date?)

//////// Please change these to your name and today's date.
String author=  "David Marques";
String title=  "Monster Mash";
String help=  "'q' to quit; 'r' to reset. ";


//// GLOBALS:  coordinates, speed, etc.
float horizon;
float puckX, puckY;       // Position.
float puckDX, puckDY;     // Speed.
float ghostX, ghostY;
float ghostDX, ghostDY;
float mouthR = 0;     //Mouth Rotation in PI
float goldX, goldY;
float butX,butY,butDia;
float sunX;

int score;



//// SETUP:  window size, initialization (start in middle of screen).
void setup() {
  size( 640,480);
  reset();
  
}

void reset(){
  horizon=  height/4;
  puckX=  width/2-20;
  puckY= horizon-40;
  puckDX=  40;
  puckDY=  40;
  ghostX=0;
  ghostY=height;
  ghostDX= 100;
  ghostDY= 100;
  goldX= random(width/5, width-(width/5));
  goldY= random(height/5, height-(height/5));
  sunX = 30;
  score= 0;
  butX= width*3/4;
  butY= height/6;
  butDia = 40;
  
}



//// NEXT FRAME:  scene, action, show.
void draw() {
  scene();
  puck();
  ghost();
  messages();
  gold();
  button();
}


void gold(){
  ///Gold Shapes
  fill(200,200,0);
  ellipse(goldX+20,goldY,20,20);
  ellipse(goldX-20,goldY,20,20);
  ellipse(goldX-10,goldY+17,20,20);
  ellipse(goldX-10,goldY-17,20,20);
  ellipse(goldX+10,goldY+17,20,20);
  ellipse(goldX+10,goldY-17,20,20);
  ellipse(goldX,goldY,20,20);
  fill(0);
  if(dist(puckX,puckY,goldX,goldY) < 60) goldReset();
}

void goldReset(){
  goldX= random(width/5, width-(width/5));
  goldY= random(height/5, height-(height/5));
  score += 50;
}




void scene() {  
  //// SCENE:  sky, sun, tree, house, etc.
  background( 100, 150, 200);                // sky
  
  
  
  //Sun across the sky
  fill( 255,255,0 );
  ellipse(sunX, height/8,30,30);
  sunX += 3;
  if(sunX > (width + 30)) sunX = -30;
  
  
  fill( 100, 200, 100);
  stroke( 100, 200, 100);
  rect( 0, horizon, width, height*3/4);      // grass.
  
  triangle( 150, horizon-25, 120, horizon-75, 180, horizon-75);  // tree
  stroke( 139, 69, 19); fill( 139, 69, 19);  
  rect( 140, horizon-50, 20, 50);
  
  stroke(0); fill(0);
  ellipse( 145, horizon-70, 2, 2);              //Tree Smile :D
  ellipse( 155, horizon-70, 2, 2);
  arc(150, horizon-64 , 10, 10, 0, PI, CHORD);
  
  //--text( "Don't let him talk to you like that! You are a beautiful tree!", 10, horizon-80 );
    
  fill(255, 0, 150);
  rect( width/2-20, horizon-40, 40, 40);              // house
  fill(255, 0, 50);
  triangle( width/2, horizon-60, width/2-30, horizon-40, width/2+30, horizon-40);

  fill(0);
  //--text( "My name is Puckman, I live an evil life.", 10,height-20 );
  //--fill(255);text( "Created By David Marques", width-150, height-10);
  //--fill(0);
  
}



void messages() {
  fill(255);
  textSize(20);
  text( title, width/3, 20 );
  text( help, width*3/5, height-20 );
  text( author, 10,height-10 );
  text( score, width-100, 20);
  textSize(10);
}



//// ACTION:  move (x,y) coordinates of puck & ghost; show them.
void puck() {

  puckX=  puckX - (puckX-goldX)/puckDX;    // ACTION:  move (puckX,puckY) coordinates.
  puckY=  puckY - (puckY-goldY)/puckDY;  
  
  translate(puckX,puckY);                          //translates to puckX,puckY for the rotate
  rotate( atan2( goldY-puckY,goldX-puckX));          //
  
  text( "WOCCA!", 15, 0 );
  text( "Puckman", -30, -45);
  
  fill(255,200,0);
  if(frameCount % 20 < 10){      //Makes the mouth open and close every 20 frames
    mouthR = mouthR + (QUARTER_PI/10);    //Puckman Shape Close
  } else{
    mouthR = mouthR - (QUARTER_PI/10);    //Puckman Shape Open
  }
  arc(0, 0, 80, 80, mouthR, TWO_PI - mouthR, PIE);
  
  fill(255,255,255);               //White for Teeth and Eye
  //line(0, -30, 10, -20);       //Puckman Eyebrow Line
  
  stroke(255, 0, 0);               //makes Teeth and Eye red   
  
/*---  
  triangle(0, 0, 5, -5, 5, 0);
  triangle(5, 5, 10, 10, 10, 5);          //too lazy to include teeth in animation          
  triangle(10, -10, 15, -15, 15, -10);    //Teeth
  triangle(15, 15, 20, 20, 20, 15);    
  triangle(20, -20, 25, -25, 25, -20);    
---*/  
  
  ellipse(-15, 0, 13, 13);        //Puckman Eye
  point(-13, 0);                //Puckman Eye Pupil
  
  stroke(0);                       //makes stroke color black again
  rotate( -atan2( goldY-puckY,goldX-puckX));          //unrotates and untranslates the rest of the code
  translate(-puckX,-puckY);                         //
  
}



void ghost() {
  ghostX=  ghostX - (ghostX-puckX)/ghostDX;              //Movement of ghost "Ghost"
  ghostY=  ghostY - (ghostY-puckY)/ghostDY;
  
  fill( 0, 255, 255 );
  rect(ghostX-30,ghostY-1, 60,50 );                //Body of ghost "Ghost"
  arc(ghostX, ghostY, 60, 60, PI, TWO_PI, OPEN);   //Head of ghost "Ghost"
  noStroke();
  
  fill(255);
  ellipse(ghostX-13,ghostY,15,15);                 //Whites of eyes of ghost
  ellipse(ghostX+13,ghostY,15,15);
  
  fill(0,0,255);
  translate(ghostX-13, ghostY);                    //Left pupil rotation 
  rotate(atan2(puckY-ghostY,puckX-ghostX));                //towards location of Hero "Puckman"
  ellipse(4,0,7,7);
  rotate(-atan2(puckY-ghostY,puckX-ghostX));
  translate(-(ghostX-13), -ghostY);
  
  translate(ghostX+13, ghostY);                    //Right pupil rotation 
  rotate(atan2(puckY-ghostY,puckX-ghostX));                //towards location of Hero "Puckman"
  ellipse(4,0,7,7);
  rotate(-atan2(puckY-ghostY,puckX-ghostX));
  translate(-(ghostX+13), -ghostY);
  
  if(dist(ghostX,ghostY,puckX,puckY) < 50) ghostReset();  
}

//reset if ghost catchs hero
void ghostReset(){
  puckX=  width/2-20;
  puckY= horizon-40;
  ghostX = 0;
  ghostY = height;
  score -= 100;
}


void button(){
  fill(255,0,0);
  ellipse(butX,butY,butDia,butDia);
}





//////// HANDLERS:  mouse clicks, keys
void mousePressed() {
  //mouse press resets
  if(dist(mouseX,mouseY,butX,butY) <= (butDia/2)) buttonReset();
}

//resetif button is pressed
void buttonReset(){
  goldX= random(width/3, width-(width/3));
  goldY= random(height/3, height-(height/3));
  puckX=  width/2-20;
  puckY= horizon-40;
  ghostX = 0;
  ghostY = height;
  score -= 50;
}

void keyPressed() {
  if (key == 'q') {
    exit();                           // press 'q' key to QUIT.
  }
  if (key == 'r') {
    reset();
  }
}

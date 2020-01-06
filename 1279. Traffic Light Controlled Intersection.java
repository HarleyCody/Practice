class TrafficLight {
    // synchronzied to make sure greenOnA will not be changed symultaneuously
    boolean greenOnA = true;
    public TrafficLight() {
        
    }
    
    public synchronized void carArrived(
        int carId,           // ID of the car
        int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
        int direction,       // Direction of the car
        Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
        Runnable crossCar    // Use crossCar.run() to make car cross the intersection 
    ) {
        if(0< direction && direction < 3){
            if(!greenOnA){
                greenOnA = true;
                turnGreen.run();
            }
            crossCar.run();
        }else{
            if(greenOnA){
                turnGreen.run();
            }
            greenOnA = false;
            crossCar.run();
        }
    }
}

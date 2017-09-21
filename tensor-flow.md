
## 1. Installing TensorFlow

    ```
    $ sudo easy_install --upgrade pip
    $ sudo easy_install --upgrade six
    ```
    
    ```
    pip install tensorflow
    ```
    
    ```
    OSError: [Errno 1] Operation not permitted: '/tmp/pip-8ur5Ju-uninstall/System/Library/Frameworks/Python.framework/Versions/2.7/Extras/lib/python/numpy-1.8.0rc1-py2.7.egg-info'
    ```
   
  https://stackoverflow.com/questions/37810228/how-to-pip-install-tensorflow-on-el-capitan

    ```
    sudo easy_install --upgrade numpy
    ```
    
    ``` python
    import tensorflow as tf
    hello = tf.constant('Hello, TensorFlow!')
    sess = tf.Session()
    print(sess.run(hello))
    ```
    
    ```
     Hello, TensorFlow!
    ```
    
  ## 2. Getting Started with TensorFlow
     
   https://www.tensorflow.org/get_started/get_started
   
### 2.1  Lower level API : TensorFlow Core
  
  Two sections of TensorFLow Core programs : 
   1. Buiding the computational graph
   2. running the computational graph 
      + **Computational Graph** is a series of TensorFlow operations arranged into graph of nodes.
     ![](https://ws4.sinaimg.cn/large/006tKfTcgy1fjr58ego0cj317y0suq57.jpg)


      + **Session** encapsulates the control and state of the TensorFlow runtime, which is used for running <u>computational graph</u> to evaluate the nodes. 
      + **placeholder** is a promise to provide a value later, which is used to accept external inputs.
      + **Variables** are used to add trainable parameters to a graph.
      + **loss function** measures how far apart the current model is from the provided data.
        - **linear regression** 
     
          We guessed the "perfect" values of `W` and `b`, but **the whole point of machine learning is to find the correct model parameters automatically**.
   
### 2.2 Higher level API : `tf.estimator`
   
+ **tensor** is central unit of data in TensorFlow, which consists of a set of primitive value shaped into an array of any number of dimensions.
  - **rank** means the number of tensor's dimensions.
+ `tf.train` API
   
   TensorFlow provides **optimizers** that slowly change each variable in order to minimize the loss function:
   - **gradient descent** optimizer modifies each variable according to the magnitude of the derivative of loss with respect to that variable.
   
+ `tf.estimator` API
   - running training loops
   - running evaluation loops
   - managing data sets
    
   
          
         
      

     
         
      
     
//
//  ViewController.swift
//  iosTest
//
//  Created by touchlab on 11/13/17.
//  Copyright © 2017 touchlab. All rights reserved.
//

import UIKit
import testdoppllib

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        CoTouchlabDopplTestingDopplJunitTestHelper.runResource(with: "dopplTests.txt")
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}


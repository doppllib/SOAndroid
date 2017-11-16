//
//  ViewController.swift
//  iosApp
//
//  Created by touchlab on 11/13/17.
//  Copyright © 2017 touchlab. All rights reserved.
//

import UIKit
import doppllib

class ViewController: UIViewController, UITableViewDataSource, SOAQuestionsViewModel_Host {
    
    private var questions = [SOAQuestion]()
    
    func setQuestionsWith(_ questions: JavaUtilList!) {
        self.questions = (JavaUtils.javaList(toList: questions) as? [SOAQuestion])!
        tableView.reloadData()
    }
    

    @IBOutlet weak var tableView: UITableView!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let viewModel: CoDopplSoArchQuestionsViewModel = SOAQuestionsViewModel()
        viewModel.register__(with: self)
        tableView.dataSource = self
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell(style: .default, reuseIdentifier: nil)
        let question: SOAQuestion = questions[indexPath.row]
        cell.textLabel?.text = question.getTitle()
        return cell
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return questions.count
    }
}

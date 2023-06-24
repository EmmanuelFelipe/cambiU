//
//  ocambiUApp.swift
//  ocambiU
//
//  Created by Luisa Franco Marzinette Pimentel on 24/06/23.
//

import SwiftUI

@main
struct ocambiUApp: App {
    let persistenceController = PersistenceController.shared

    var body: some Scene {
        WindowGroup {
            ContentView()
                .environment(\.managedObjectContext, persistenceController.container.viewContext)
        }
    }
}

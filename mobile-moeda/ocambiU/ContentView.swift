//
//  ContentView.swift
//  ocambiU
//
//

import SwiftUI
import CoreData

struct ContentView: View {
    @Environment(\.managedObjectContext) private var viewContext
    @FetchRequest(
        sortDescriptors: [NSSortDescriptor(keyPath: \Item.timestamp, ascending: true)],
        animation: .default)
    private var items: FetchedResults<Item>

    let moedas = [
        Moeda(codigo: "ec7a3dad-619f-498c-9628-bebe0b6b3fe1", nome: "Euro", sigla: "eur", descricao: "Moeda da união europeia"),
        Moeda(codigo: "53789b93-e49b-4466-8f7c-8d725156e372", nome: "Dolar", sigla: "usd", descricao: "Moeda dos Estados Unidos"),
        Moeda(codigo: "3afb325a-75f0-4d2a-80e3-4807989e8640", nome: "Real", sigla: "brl", descricao: "Moeda do Brasil")
    ]

    let cotacao = [
        Cotacao(codigo:"5a5d4b07-031d-41d1-a271-c304d369acd5",origem:"USD",destino:"BRL",nome:"Dólar Americano/Real Brasileiro",compra:4.7864,venda:4.7891,dataConsulta:"2023-06-23 17:59:56",dataCriacao:"2023-06-24T15:43:11.057366"),
        Cotacao(codigo:"24b2e8fa-6076-4324-a8bb-afffee21360b",origem:"EUR",destino:"BRL",nome:"Euro/Real Brasileiro",compra:5.2078,venda:5.2158,dataConsulta:"2023-06-23 17:59:43",dataCriacao:"2023-06-24T15:43:30.04239")
    ]
    
    var body: some View {
        VStack(alignment: .leading) {
            List(moedas) { moeda in
                MoedaRow(moeda: moeda)
            }
        }
        
        VStack(alignment: .leading) {
            List(cotacao) { cotacao in
                CotacaoRow(cotacao: cotacao)
            }
        }
    }

    struct Moeda: Identifiable {
        let id = UUID()
        let codigo: String
        let nome: String
        let sigla: String
        let descricao: String
    }
    
    struct Cotacao: Identifiable {
        let id = UUID()
        let codigo: String
        let origem: String
        let destino: String
        let nome: String
        let compra: Double
        let venda: Double
        let dataConsulta: String
        let dataCriacao: String
    }

    struct MoedaRow: View {
        var moeda: Moeda
        
        var body: some View {
            Section(header: HStack() {
                Text(moeda.nome)
                Spacer()
                Button("Abrir") {}
            })
            {
                Text("Sigla: \(moeda.sigla)")
                Text("Descrição: \(moeda.descricao)")
            }
            .headerProminence(.increased)
        }
    }
    
    struct CotacaoRow: View {
        var cotacao: Cotacao
        
        var body: some View {
            Section(header: HStack() {
                Text("Cotação \(cotacao.origem) → \(cotacao.destino)")
                Spacer()
                Button("Selecionar") {}
            })
            {
                Text(1.formatted(.currency(code: cotacao.origem)) + (cotacao.origem) + " = "  + cotacao.compra.formatted(.currency(code: cotacao.destino)) + (cotacao.destino))
            }
            .headerProminence(.increased)
        }
    }

    struct ContentView_Previews: PreviewProvider {
        static var previews: some View {
            ContentView().environment(\.managedObjectContext, PersistenceController.preview.container.viewContext)
        }
    }
}

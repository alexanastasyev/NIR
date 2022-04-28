import React from "react";
import DataTable from 'react-data-table-component';

import "./Table.css"

class Table extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            data: props.data
        }

    }

    render() {
        const columns = [
            {
                name: "ID",
                selector: row => row.id
            },
            {
                name: "Birth year",
                selector: row => row.birthYear
            },
            {
                name: "Education",
                selector: row => row.education
            },
            {
                name: "Marital status",
                selector: row => row.maritalStatus
            },
            {
                name: "Income",
                selector: row => row.income
            },
            {
                name: "Kids",
                selector: row => row.kidsAmount
            },
            {
                name: "Teens",
                selector: row => row.teensAmount
            },
            {
                name: "Date of enrollment",
                selector: row => row.enrollmentDate
            },
            {
                name: "Recency",
                selector: row => row.recency
            },
            {
                name: "Complains",
                selector: row => row.complains
            },
            {
                name: "Wine bought",
                selector: row => row.wineAmount
            },
            {
                name: "Fruits bought",
                selector: row => row.fruitsAmount
            },
            {
                name: "Meat bought",
                selector: row => row.meatAmount
            },
            {
                name: "Fish bought",
                selector: row => row.fishAmount
            },
            {
                name: "Sweet bought",
                selector: row => row.sweetAmount
            },
            {
                name: "Gold bought",
                selector: row => row.goldAmount
            },
            {
                name: "Discount purchases",
                selector: row => row.discountPurchasesAmount
            },
            {
                name: "Campaign 1",
                selector: row => row.acceptedCampaign1
            },
            {
                name: "Campaign 2",
                selector: row => row.acceptedCampaign2
            },
            {
                name: "Campaign 3",
                selector: row => row.acceptedCampaign3
            },
            {
                name: "Campaign 4",
                selector: row => row.acceptedCampaign4
            },
            {
                name: "Campaign 5",
                selector: row => row.acceptedCampaign5
            },
            {
                name: "Last campaign",
                selector: row => row.response
            },
            {
                name: "Web purchases",
                selector: row => row.webPurchasesAmount
            },
            {
                name: "Catalog purchases",
                selector: row => row.catalogPurchasesAmount
            },
            {
                name: "Store purchases",
                selector: row => row.storePurchasesAmount
            },
            {
                name: "Web site visits",
                selector: row => row.visitsAmount
            }
        ];

        return (
            <div>
                <DataTable
                    columns={columns}
                    data={this.state.data}
                    pagination={true}
                />
            </div>

        );
    }
}

export default Table;
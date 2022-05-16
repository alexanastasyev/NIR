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
                selector: row => row.id,
                sortable: true
            },
            {
                name: "Birth year",
                selector: row => row.birthYear,
                sortable: true
            },
            {
                name: "Education",
                selector: row => row.education,
                sortable: true
            },
            {
                name: "Marital status",
                selector: row => row.maritalStatus,
                sortable: true
            },
            {
                name: "Income",
                selector: row => row.income,
                sortable: true
            },
            {
                name: "Kids",
                selector: row => row.kidsAmount,
                sortable: true
            },
            {
                name: "Teens",
                selector: row => row.teensAmount,
                sortable: true
            },
            {
                name: "Date of enrollment",
                selector: row => row.enrollmentDate,
                sortable: true
            },
            {
                name: "Recency",
                selector: row => row.recency,
                sortable: true
            },
            {
                name: "Complains",
                selector: row => row.complains,
                sortable: true
            },
            {
                name: "Wine bought",
                selector: row => row.wineAmount,
                sortable: true
            },
            {
                name: "Fruits bought",
                selector: row => row.fruitsAmount,
                sortable: true
            },
            {
                name: "Meat bought",
                selector: row => row.meatAmount,
                sortable: true
            },
            {
                name: "Fish bought",
                selector: row => row.fishAmount,
                sortable: true
            },
            {
                name: "Sweet bought",
                selector: row => row.sweetAmount,
                sortable: true
            },
            {
                name: "Gold bought",
                selector: row => row.goldAmount,
                sortable: true
            },
            {
                name: "Discount purchases",
                selector: row => row.discountPurchasesAmount,
                sortable: true
            },
            {
                name: "Campaign 1",
                selector: row => row.acceptedCampaign1,
                sortable: true
            },
            {
                name: "Campaign 2",
                selector: row => row.acceptedCampaign2,
                sortable: true
            },
            {
                name: "Campaign 3",
                selector: row => row.acceptedCampaign3,
                sortable: true
            },
            {
                name: "Campaign 4",
                selector: row => row.acceptedCampaign4,
                sortable: true
            },
            {
                name: "Campaign 5",
                selector: row => row.acceptedCampaign5,
                sortable: true
            },
            {
                name: "Last campaign",
                selector: row => row.response,
                sortable: true
            },
            {
                name: "Web purchases",
                selector: row => row.webPurchasesAmount,
                sortable: true
            },
            {
                name: "Catalog purchases",
                selector: row => row.catalogPurchasesAmount,
                sortable: true
            },
            {
                name: "Store purchases",
                selector: row => row.storePurchasesAmount,
                sortable: true
            },
            {
                name: "Website visits",
                selector: row => row.visitsAmount,
                sortable: true
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